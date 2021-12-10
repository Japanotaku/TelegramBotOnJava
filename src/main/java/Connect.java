
import java.sql.*;

public class Connect {
    public static Connection conn;
    public static Statement statement;
    public static ResultSet resultSet;
    public static PreparedStatement preparedStatement;

    public static Connection ConnectDb() throws SQLException {
        try {
            String url = "jdbc:postgresql://localhost/postgres";
            String user = "postgres";
            String pass = "";
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}


