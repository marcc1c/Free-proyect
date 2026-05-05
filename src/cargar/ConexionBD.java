package cargar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static String url = "jdbc:mysql://localhost:3306/bbdd_call_the_best";
    private static String usuario = "root";
    private static String password = "";

    public static Connection conectar() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }

        return conexion;
    }
}