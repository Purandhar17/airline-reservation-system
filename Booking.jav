import java.sql.*;

public class Booking {
    public static void bookFlight(int userId, int flightId, String seat) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO bookings(user_id, flight_id, seat_number) VALUES (?, ?, ?)");
            stmt.setInt(1, userId);
            stmt.setInt(2, flightId);
            stmt.setString(3, seat);
            stmt.executeUpdate();
            System.out.println("Booking successful.");
        } catch (SQLException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    public static void viewBookings(int userId) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT b.id, f.flight_number, f.origin, f.destination, f.date, b.seat_number " +
                "FROM bookings b JOIN flights f ON b.flight_id = f.id WHERE b.user_id = ?");
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Your Bookings:");
            while (rs.next()) {
                System.out.println("Booking ID: " + rs.getInt("id") +
                        ", Flight: " + rs.getString("flight_number") +
                        ", From: " + rs.getString("origin") +
                        ", To: " + rs.getString("destination") +
                        ", Date: " + rs.getDate("date") +
                        ", Seat: " + rs.getString("seat_number"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching bookings: " + e.getMessage());
        }
    }
}
