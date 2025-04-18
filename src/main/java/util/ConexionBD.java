package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static String url = "jdbc:mysql://127.0.0.1:3306/jardineria";
    private static String user = "root";
    private static String password = "Suigintou.89";

    private static Connection con;

    public static Connection getConexion() throws SQLException {
        if (con == null) {
            con = DriverManager.getConnection(url, user, password);
        }
        return con;
    }
}
