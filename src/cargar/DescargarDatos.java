package cargar;

import invocaciones.Invocacion;
import items.Items;
import logica.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DescargarDatos {

    private final CargarDatos cargarDatos = new CargarDatos();

    public boolean registraUsuario(String nombreUsuario, char[] contrasenaArray) {
        boolean registrado = false;
        String contrasena = new String(contrasenaArray);

        if (!cargarDatos.existeUsuario(nombreUsuario)) {
            String insert = "INSERT INTO USUARIO (nombre, contrasena_hash) VALUES (?, ?)";
            Connection conexion = null;

            try {
                conexion = ConexionBD.conectar();

                if (conexion != null) {
                    PreparedStatement ps = conexion.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, nombreUsuario);
                    ps.setString(2, contrasena);
                    int filasAfectadas = ps.executeUpdate();

                    if (filasAfectadas > 0) {
                        ResultSet keys = ps.getGeneratedKeys();
                        if (keys.next()) {
                            int idUsuario = keys.getInt(1);
                            crearProgresoCombate(idUsuario, conexion);
                            crearItemsUsuarioIniciales(idUsuario, conexion);
                            crearLogrosUsuarioIniciales(idUsuario, conexion);
                        }
                        keys.close();
                        registrado = true;
                        System.out.println("Usuario registrado correctamente");
                    }

                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al registrar usuario: " + e.getMessage());
                registrado = false;
            } finally {
                cerrarConexion(conexion);
            }
        }

        return registrado;
    }

    public void guardarPartida(int idUsuario) {
        Connection conexion = null;

        if (idUsuario > 0) {
            try {
                conexion = ConexionBD.conectar();

                if (conexion != null) {
                    conexion.setAutoCommit(false);
                    guardarProgresoCombate(idUsuario, conexion);
                    guardarItemsUsuario(idUsuario, conexion);
                    guardarInvocaciones(idUsuario, conexion);
                    conexion.commit();
                    System.out.println("Partida guardada correctamente");
                }
            } catch (SQLException e) {
                System.out.println("Error al guardar la partida: " + e.getMessage());
                try {
                    conexion.rollback();
                } catch (SQLException ex) {
                    System.out.println("Error al revertir: " + ex.getMessage());
                }
            } finally {
                if (conexion != null) {
                    try {
                        conexion.setAutoCommit(true);
                    } catch (SQLException ignored) {
                    }
                }
                cerrarConexion(conexion);
            }
        }
    }

    private void crearProgresoCombate(int idUsuario, Connection conexion) throws SQLException {
        String insert = "INSERT INTO PROGRESO_COMBATE (id_usuario, piso_torre_infinita, nivel_campana, piso_campana) " +
                "VALUES (?, 1, 1, 1)";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setInt(1, idUsuario);
        ps.executeUpdate();
        ps.close();
    }

    private void crearItemsUsuarioIniciales(int idUsuario, Connection conexion) throws SQLException {
        String insert = "INSERT INTO ITEM_USUARIO (id_usuario, id_item, cantidad) " +
                "SELECT ?, id, 0 FROM ITEM";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setInt(1, idUsuario);
        ps.executeUpdate();
        ps.close();
    }

    private void crearLogrosUsuarioIniciales(int idUsuario, Connection conexion) throws SQLException {
        String insert = "INSERT INTO LOGRO_USUARIO (id_usuario, id_logro, progreso) " +
                "SELECT ?, id, 0 FROM LOGRO";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setInt(1, idUsuario);
        ps.executeUpdate();
        ps.close();
    }

    private void guardarProgresoCombate(int idUsuario, Connection conexion) throws SQLException {
        String update = "UPDATE PROGRESO_COMBATE SET " +
                "piso_torre_infinita = ?, nivel_campana = ?, piso_campana = ? " +
                "WHERE id_usuario = ?";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setInt(1, Main.pisoTorreInfinita);
        ps.setInt(2, Main.nivelCampana);
        ps.setInt(3, Main.pisoCampana);
        ps.setInt(4, idUsuario);
        ps.executeUpdate();
        ps.close();
        System.out.println("Guardado progreso combate correctamente");
    }

    private void guardarItemsUsuario(int idUsuario, Connection conexion) throws SQLException {
        String update = "UPDATE ITEM_USUARIO SET cantidad = ? WHERE id_usuario = ? AND id_item = ?";
        PreparedStatement ps = conexion.prepareStatement(update);

        for (Items item : Main.catalogoItems) {
            ps.setInt(1, item.getCantidad());
            ps.setInt(2, idUsuario);
            ps.setInt(3, item.getId());
            ps.addBatch();
        }

        ps.executeBatch();
        ps.close();
        System.out.println("Guardados objetos usuario correctamente");
    }

    private void guardarInvocaciones(int idUsuario, Connection conexion) throws SQLException {
        PreparedStatement psDelete = conexion.prepareStatement(
                "DELETE FROM INVOCACION WHERE id_usuario = ?"
        );
        psDelete.setInt(1, idUsuario);
        psDelete.executeUpdate();
        psDelete.close();

        String insert = "INSERT INTO INVOCACION (" +
                "id_usuario, id_en_partida, nivel, ascension, raza, rareza, " +
                "experiencia, experiencia_maxima, vida, vida_maxima, ataque, defensa, " +
                "prob_critico, dano_critico, multi_vida, multi_ataque, multi_defensa, " +
                "multi_prob_critico, multi_dano_critico, multi_experiencia" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement psInsert = conexion.prepareStatement(insert);

        for (Invocacion invocacion : Main.inventarioInvocaciones) {
            psInsert.setInt(1, idUsuario);
            psInsert.setInt(2, invocacion.getId());
            psInsert.setInt(3, invocacion.getNivel());
            psInsert.setInt(4, invocacion.getAscension());
            psInsert.setString(5, invocacion.getRaza());
            psInsert.setString(6, invocacion.getRareza());
            psInsert.setDouble(7, invocacion.getExperiencia());
            psInsert.setDouble(8, invocacion.getExperienciaMaxima());
            psInsert.setDouble(9, invocacion.getVida());
            psInsert.setDouble(10, invocacion.getVidaMaxima());
            psInsert.setDouble(11, invocacion.getAtaque());
            psInsert.setDouble(12, invocacion.getDefensa());
            psInsert.setDouble(13, invocacion.getProbCritico());
            psInsert.setDouble(14, invocacion.getDañoCritico());
            psInsert.setDouble(15, invocacion.getMultiVida());
            psInsert.setDouble(16, invocacion.getMultiAtaque());
            psInsert.setDouble(17, invocacion.getMultiDefensa());
            psInsert.setDouble(18, invocacion.getMultiProbCritico());
            psInsert.setDouble(19, invocacion.getMultiDañoCritico());
            psInsert.setDouble(20, invocacion.getMultiExteriencia());
            psInsert.addBatch();
        }

        psInsert.executeBatch();
        psInsert.close();
        System.out.println("Guardadas invocaciones usuario correctamente");
    }

    private void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
