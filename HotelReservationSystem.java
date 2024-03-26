import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class HotelReservationSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static int reservationId = 1;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Rooms();
        while (true) {
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    DisplayAvailableRooms();break;
                case 2:
                    MakeReservation(scanner);break;
                case 3:
                    BookingDetails(scanner);break;
                case 4:
                    System.out.println("Exiting...");return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void Rooms() {
        rooms.add(new Room(101, "Single", 500.0));
        rooms.add(new Room(102, "Double", 1000.0));
        rooms.add(new Room(103, "Suite", 2500.0));
    }
    private static void DisplayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {System.out.println(room);
            }
        }
    }
    private static void MakeReservation(Scanner scanner) {
        System.out.println("Enter room number to reserve:");
        int roomNumber = scanner.nextInt();
        Room room = findRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("Room not found.");return;
        }
        System.out.println("Enter check-in date (YYYY-MM-DD):");
        String checkInDate = scanner.next();
        System.out.println("Enter check-out date (YYYY-MM-DD):");
        String checkOutDate = scanner.next();
        Reservation reservation = new Reservation(reservationId++, roomNumber, checkInDate, checkOutDate);
        reservations.add(reservation);
        room.setAvailable(false);
        System.out.println("Reservation successful. Your reservation ID is: " + reservation.getId());
    }
    private static void BookingDetails(Scanner scanner) {
        System.out.println("Enter reservation ID:");
        int reservationId = scanner.nextInt();
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                System.out.println("Booking details:\n" + reservation);return;
            }
        }
        System.out.println("Reservation not found.");}
    private static Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getNumber() == roomNumber && room.isAvailable()) {
                return room;
            }
        }
        return null;
    }
    private static class Room {
        private int number;
        private String type;
        private double price;
        private boolean available;
        public Room(int number, String type, double price) {
            this.number = number;
            this.type = type;
            this.price = price;
            this.available = true;
        }
        public int getNumber() { return number;}
        @SuppressWarnings("unused")
        public String getType() { return type;}
        public double getPrice() { return price;}
        public boolean isAvailable() { return available;}
        public void setAvailable(boolean available){ this.available=available;
            
        }
        @Override
        public String toString() {
            return "Room " + number + " (" + type + ") - $" + price + " per night";}
    }
    private static class Reservation {
        private int id;
        private int roomNumber;
        private String checkInDate;
        private String checkOutDate;
        public Reservation(int id, int roomNumber, String checkInDate, String checkOutDate) {
            this.id = id;this.roomNumber = roomNumber;
            this.checkInDate = checkInDate;this.checkOutDate = checkOutDate;
        }
        public int getId() {return id;}
        @Override
        public String toString() {
            return "Reservation ID: " + id + "\nRoom Number: " + roomNumber + "\nCheck-in Date: " + checkInDate + "\nCheck-out Date: " + checkOutDate;
        }
    }
}