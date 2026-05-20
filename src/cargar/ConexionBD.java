package cargar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String url = "jdbc:mysql://localhost:3306/bbdd_call_the_best";
    private static final String usuario = "root";
    private static final String password = "mysql";

    public static Connection conectar() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }

        return conexion;
    }
}
