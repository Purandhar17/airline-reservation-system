import java.sql.*;
import java.util.Scanner;

public class User {
    public static boolean login(String username, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
            return false;
        }
    }

    public static void register(String username, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(username, password) VALUES (?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("User registered successfully.");
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }
}
