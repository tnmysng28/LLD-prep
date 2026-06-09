
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


enum RoomType{
    STANDARD, 
    DELUXE,
    SUITE
}

enum RoomStatus {
    ACTIVE,              // Room is operational
    MAINTENANCE,         // Room needs repair
    OUT_OF_SERVICE       // Room is closed
}

class Room {
    int roomNumber;
    RoomStatus status;           // Maintenance status only
    RoomType type;
    double pricePerNight;
    List<Reservation> reservations;

    RoomType getRoomType() {
        return type;
    }

    RoomStatus getStatus() {
        return status;
    }

    // Comprehensive availability check - considers both status and reservations
    public boolean isAvailable(LocalDate checkIn, LocalDate checkOut) {
        // 1. Check if room is operational
        if (status != RoomStatus.ACTIVE) {
            return false;
        }

        // 2. Check for reservation date conflicts
        if (reservations == null || reservations.isEmpty()) {
            return true;
        }
        
        for (Reservation reservation : reservations) {
            if (overlaps(
                    reservation.getCheckInDate(),
                    reservation.getCheckOutDate(),
                    checkIn,
                    checkOut)) {
                return false;
            }
        }

        return true;
    }



    private boolean overlaps(
            LocalDate existingStart,
            LocalDate existingEnd,
            LocalDate requestedStart,
            LocalDate requestedEnd) {

        return existingStart.isBefore(requestedEnd)
                && existingEnd.isAfter(requestedStart);
    }

    public void addReservation(
            Reservation reservation) {

        reservations.add(reservation);
    }
}



class Guest{
    String id;
    String name;

}
enum ReservationStatus{
    BOOKED,
    CANCELLED, 
    CHECKED_IN, 
    CHECKED_OUT
}
class Reservation{
    String id;
    Guest guest;
    Room room;
    LocalDate checkInDate;
    LocalDate checkOutDate;;
    ReservationStatus status;

    LocalDate getCheckInDate(){
        return checkInDate;
    }
    LocalDate getCheckOutDate(){
        return checkOutDate;
    }


}

class Hotel{
    String id;
    String name;
    List<Room> rooms;

    // Constructor - initialize rooms list
    public Hotel(String id, String name){
        this.id = id;
        this.name = name;
        this.rooms = new ArrayList<>();
    }
    
    // Add a room to the hotel
    public void addRoom(Room room) {
        rooms.add(room);
    }
 


}

interface PricingStrategy {

    double calculatePrice(Reservation reservation);
}


// RegularPricingStrategy
// WeekendPricingStrategy
// FestivalPricingStrategy

class RoomSearchService {

    // Get available rooms by type and date range
    public List<Room> getAvailableRooms(
            Hotel hotel, 
            RoomType type, 
            LocalDate checkIn, 
            LocalDate checkOut) {
        
        // Validate inputs
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel cannot be null");
        }
        if (type == null) {
            throw new IllegalArgumentException("Room type cannot be null");
        }
        if (checkIn == null || checkOut == null) {
            throw new IllegalArgumentException("Check-in and check-out dates cannot be null");
        }
        if (checkIn.isAfter(checkOut) || checkIn.isEqual(checkOut)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date");
        }

        List<Room> availableRooms = new ArrayList<>();
        
        // Get all rooms from hotel
        for (Room room : hotel.rooms) {
            if (room == null) {
                continue;
            }
            
            // Filter by type
            if (room.getRoomType() != type) {
                continue;
            }
            
            // Check if room is available for the given dates
            // This now considers both maintenance status AND reservations
            if (room.isAvailable(checkIn, checkOut)) {
                availableRooms.add(room);
            }
        }
        
        return availableRooms;
    }

}