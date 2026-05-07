package cargar;

import items.Items;
import items.LootEntry;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConexionBD {

    private static String url = "jdbc:mysql://localhost:3306/bbdd_call_the_best";
    private static String usuario = "root";
    private static String password = "mysql";

    public static Connection conectar() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conexion;
    }

    public Map<String, List<LootEntry>> cargarPoolObjetosDrop() {
        Map<String, List<LootEntry>> lootPorCalidad = new HashMap<>();

        String select = "SELECT calidad_enemigo, id_item, porcentaje, cantidad_minima, cantidad_maxima FROM LOOT_CALIDAD";

        try {
            Connection conexion = ConexionBD.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(select);

            while (rs.next()) {
                String calidadEnemigo = rs.getString("calidad_enemigo");
                int idObjeto = rs.getInt("id_objeto");
                double porcentaje = rs.getDouble("porcentaje");
                int cantidadMinima = rs.getInt("cantidad_minima");
                int cantidadMaxima = rs.getInt("cantidad_maxima");

                LootEntry lootEntry = new LootEntry(idObjeto, porcentaje, cantidadMinima, cantidadMaxima);

                if (!lootPorCalidad.containsKey(calidadEnemigo)) {
                    lootPorCalidad.put(calidadEnemigo, new ArrayList<>());
                }

                lootPorCalidad.get(calidadEnemigo).add(lootEntry);
            }

            rs.close();
            st.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lootPorCalidad;
    }

    public ArrayList<Items> cargarItemsUsuario(int idUsuario) {
        ArrayList<Items> items = new ArrayList<>();

        String select = "SELECT i.id, i.nombre, i.descripcion, i.rareza, " +
                "COALESCE(iu.cantidad, 0) AS cantidad " +
                "FROM ITEM i " +
                "LEFT JOIN ITEM_USUARIO iu ON i.id = iu.id_item AND iu.id_usuario = " + idUsuario;

        try {
            Connection conexion = ConexionBD.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(select);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String rareza = rs.getString("rareza");
                int cantidad = rs.getInt("cantidad");

                Items item = new Items(nombre, descripcion, rareza, cantidad, id);

                items.add(item);
            }

            rs.close();
            st.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return items;
    }

    public int iniciarSesion(String nombreUsuario, String contrasena) {

        int idUsuario = -1;

        String select = "SELECT id FROM USUARIO WHERE nombre = '" + nombreUsuario +
                "' AND contrasena_hash = '" + contrasena + "'";

        try {
            Connection conexion = ConexionBD.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(select);

            if (rs.next()) {
                idUsuario = rs.getInt("id");
            }

            rs.close();
            st.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return idUsuario;
    }

    public boolean existeUsuario(String nombreUsuario) {

        boolean existe = false;

        String select = "SELECT id FROM USUARIO WHERE nombre = '" + nombreUsuario + "'";

        try {
            Connection conexion = ConexionBD.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(select);

            if (rs.next()) {
                existe = true;
            }

            rs.close();
            st.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return existe;
    }

    public boolean registraUsuario (String nombreUsuario, String contrasena) {

        boolean seHaRegistrado = false;

        if (!existeUsuario(nombreUsuario)) {

            String insert = "INSERT INTO USUARIO (nombre, contrasena_hash) VALUES ('" + nombreUsuario  + "', '"  + contrasena + "'";
            try {
                Connection conexion = ConexionBD.conectar();
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(insert);

                rs.close();
                st.close();
                conexion.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            seHaRegistrado = true;

        }

        return seHaRegistrado;
    }

}