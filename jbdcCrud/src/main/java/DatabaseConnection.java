import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String url = "jdbc:mysql://localhost:3306/jdbc_tutorial";
    private static final String user = "****";
    private static final String password = "*********";

    public static Connection getConnection() {
        Connection conn = null;
        try{
            conn =  DriverManager.getConnection(url, user, password);
            String createTable = "CREATE TABLE IF NOT EXISTS users" +
                    "( id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(100)," +
                    "email VARCHAR(100) UNIQUE"
                    +")";
            try(PreparedStatement smt = conn.prepareStatement(createTable)){
                smt.executeUpdate();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database");
        }
        return conn;
    }
}
