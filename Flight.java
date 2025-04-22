import java.sql.*;

public class Flight {
    public static void listFlights() {
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM flights");

            System.out.println("Available Flights:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Flight No: " + rs.getString("flight_number") +
                        ", From: " + rs.getString("origin") +
                        ", To: " + rs.getString("destination") +
                        ", Date: " + rs.getDate("date"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching flights: " + e.getMessage());
        }
    }
}
