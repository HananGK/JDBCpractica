import java.sql.*;

public class JDBCinicio {
    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/jardineria";
        String user = "root";
        String password = "Suigintou.89";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery("SELECT * FROM cliente");
            while (rst.next()) {
                System.out.println(rst.getInt("codigo_cliente") + " " + rst.getString("nombre_cliente"));
            }
            rst.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
