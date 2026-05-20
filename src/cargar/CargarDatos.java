package cargar;

import invocaciones.*;
import items.Items;
import items.LootEntry;
import logica.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class CargarDatos {

    public void cargarJuegoCompleto(int idUsuario, Map<String, ArrayList<LootEntry>> lootPorCalidad) {
        cargarItemsUsuario(idUsuario);
        cargarPoolObjetosDrop(lootPorCalidad);
        cargarInvocaciones(idUsuario);
        cargarDatosCombate(idUsuario);
    }

    public void cargarPoolObjetosDrop(Map<String, ArrayList<LootEntry>> lootPorCalidad) {
        String select = "SELECT calidad_enemigo, id_item, porcentaje, cantidad_minima, cantidad_maxima FROM LOOT_CALIDAD";

        try {
            Connection conexion = ConexionBD.conectar();

            if (conexion != null) {
                PreparedStatement ps = conexion.prepareStatement(select);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String calidadEnemigo = rs.getString("calidad_enemigo");
                    int idObjeto = rs.getInt("id_item");
                    double porcentaje = rs.getDouble("porcentaje");
                    int cantidadMinima = rs.getInt("cantidad_minima");
                    int cantidadMaxima = rs.getInt("cantidad_maxima");

                    LootEntry lootEntry = new LootEntry(cantidadMinima, idObjeto, cantidadMaxima, porcentaje);

                    if (!lootPorCalidad.containsKey(calidadEnemigo)) {
                        lootPorCalidad.put(calidadEnemigo, new ArrayList<>());
                    }

                    lootPorCalidad.get(calidadEnemigo).add(lootEntry);
                }

                rs.close();
                ps.close();
                conexion.close();
                System.out.println("Leido pool de loot correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar loot: " + e.getMessage());
        }
    }

    public void cargarItemsUsuario(int idUsuario) {
        String select = "SELECT i.id, i.nombre, i.descripcion, i.rareza, " +
                "COALESCE(iu.cantidad, 0) AS cantidad " +
                "FROM ITEM i " +
                "LEFT JOIN ITEM_USUARIO iu ON i.id = iu.id_item AND iu.id_usuario = ?";

        try {
            Connection conexion = ConexionBD.conectar();

            if (conexion != null) {
                PreparedStatement ps = conexion.prepareStatement(select);
                ps.setInt(1, idUsuario);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Items item = new Items(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("rareza"),
                        rs.getInt("cantidad"),
                        rs.getInt("id")
                );
                    Main.catalogoItems.add(item);
                }

                rs.close();
                ps.close();
                conexion.close();
                System.out.println("Leidos objetos usuario correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar items: " + e.getMessage());
        }
    }

    public void cargarInvocaciones(int idUsuario) {
        String select = "SELECT * FROM INVOCACION WHERE id_usuario = ?";

        try {
            Connection conexion = ConexionBD.conectar();

            if (conexion != null) {
                PreparedStatement ps = conexion.prepareStatement(select);
                ps.setInt(1, idUsuario);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Invocacion invocacion = crearInvocacionDesdeResultSet(rs);
                    if (invocacion != null) {
                        Main.inventarioInvocaciones.add(invocacion);
                    }
                }

                rs.close();
                ps.close();
                conexion.close();
                System.out.println("Leidas invocaciones usuario correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar invocaciones: " + e.getMessage());
        }
    }

    public void cargarDatosCombate(int idUsuario) {
        String select = "SELECT * FROM PROGRESO_COMBATE WHERE id_usuario = ?";

        try {
            Connection conexion = ConexionBD.conectar();

            if (conexion != null) {
                PreparedStatement ps = conexion.prepareStatement(select);
                ps.setInt(1, idUsuario);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Main.pisoTorreInfinita = rs.getInt("piso_torre_infinita");
                    Main.nivelCampana = rs.getInt("nivel_campana");
                    Main.pisoCampana = rs.getInt("piso_campana");
                }

                rs.close();
                ps.close();
                conexion.close();
                System.out.println("Leido progreso combate correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar progreso: " + e.getMessage());
        }
    }

    public int iniciarSesion(String nombreUsuario, char[] contrasenaArray) {
        int idUsuario = -1;
        String contrasena = new String(contrasenaArray);

        String select = "SELECT id FROM USUARIO WHERE nombre = ? AND contrasena_hash = ?";

        try {
            Connection conexion = ConexionBD.conectar();

            if (conexion != null) {
                PreparedStatement ps = conexion.prepareStatement(select);
                ps.setString(1, nombreUsuario);
                ps.setString(2, contrasena);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    idUsuario = rs.getInt("id");
                    System.out.println("Inicio de sesion correcto");
                }

                rs.close();
                ps.close();
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesion: " + e.getMessage());
        }

        return idUsuario;
    }

    public boolean existeUsuario(String nombreUsuario) {
        boolean existe = false;
        String select = "SELECT id FROM USUARIO WHERE nombre = ?";

        try {
            Connection conexion = ConexionBD.conectar();

            if (conexion != null) {
                PreparedStatement ps = conexion.prepareStatement(select);
                ps.setString(1, nombreUsuario);
                ResultSet rs = ps.executeQuery();
                existe = rs.next();

                rs.close();
                ps.close();
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al comprobar usuario: " + e.getMessage());
        }

        return existe;
    }

    private Invocacion crearInvocacionDesdeResultSet(ResultSet rs) {
        Invocacion invocacion = null;

        try {
            String raza = rs.getString("raza");
            invocacion = crearInvocacionVacia(raza);

            if (invocacion != null) {
                invocacion.restaurarDesdePartida(
                        rs.getInt("id_en_partida"),
                        rs.getInt("ascension"),
                        rs.getInt("nivel"),
                        raza,
                        rs.getString("rareza"),
                        rs.getDouble("experiencia"),
                        rs.getDouble("vida")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al leer invocacion: " + e.getMessage());
            invocacion = null;
        }

        return invocacion;
    }

    private Invocacion crearInvocacionVacia(String raza) {
        Invocacion invocacion = null;

        switch (raza) {
            case "Felino":
                invocacion = new Felino();
                break;
            case "Ave":
                invocacion = new Ave();
                break;
            case "Acuatico":
                invocacion = new Acuatico();
                break;
            case "Insecto":
                invocacion = new Insecto();
                break;
            default:
                break;
        }

        return invocacion;
    }
}
