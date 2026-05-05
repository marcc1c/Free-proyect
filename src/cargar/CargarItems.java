package cargar;

import items.Items;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CargarItems {

    public ArrayList<Items> cargarItems() {
        ArrayList<Items> items = new ArrayList<>();

        String select = "SELECT id, nombre, descripcion, rareza FROM OBJETO";

        try {
            Connection conexion = ConexionBD.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(select);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String rareza = rs.getString("rareza");

                Items item = new Items(nombre, descripcion, rareza, 0, id);

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