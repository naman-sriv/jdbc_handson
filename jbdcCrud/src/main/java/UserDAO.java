import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDAO {

    public void AddUser(String name, String email){
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement smt = conn.prepareStatement(sql)){
            smt.setString(1, name);
            smt.setString(2, email);
            smt.executeUpdate();
        } catch(SQLException e) {
            if (e.getSQLState().equals("23000")) { // SQL state for duplicate entry
                System.out.println("Error: Duplicate entry for email " + email);
            } else {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement smt = conn.prepareStatement(sql)){

            ResultSet rs = smt.executeQuery();
            while(rs.next()) {
                users.add(new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return users;
    }

    public void updateUser(int id, String name, String email) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, id);

            // Print the query with actual values
            System.out.println("Executing query: UPDATE users SET name = '" + name + "', email = '" + email + "' WHERE id = " + id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User with ID " + id + " updated successfully.");
            } else {
                System.out.println("No user found with ID " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteUser(int id){
        String sql = "DELETE FROM users WHERE id=?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement smt = conn.prepareStatement(sql)){
            smt.setInt(1,id);
            smt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
