import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userId = -1;

        while (true) {
            System.out.println("\n--- Airline Reservation System ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                User.register(username, password);
            } else if (option == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                if (User.login(username, password)) {
                    System.out.println("Login successful.");
                    userId = 1; // Simulated user_id (replace with actual logic later)
                    break;
                } else {
                    System.out.println("Invalid credentials.");
                }
            } else if (option == 3) {
                System.exit(0);
            }
        }

        // After login
        while (true) {
            System.out.println("\n--- Dashboard ---");
            System.out.println("1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. My Bookings");
            System.out.println("4. Logout");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                Flight.listFlights();
            } else if (choice == 2) {
                System.out.print("Enter flight ID to book: ");
                int flightId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter seat number: ");
                String seat = scanner.nextLine();
                Booking.bookFlight(userId, flightId, seat);
            } else if (choice == 3) {
                Booking.viewBookings(userId);
            } else if (choice == 4) {
                System.out.println("Logged out.");
                break;
            }
        }

        scanner.close();
    }
}
