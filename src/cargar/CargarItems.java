package cargar;

import items.Items;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CargarItems {

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
}