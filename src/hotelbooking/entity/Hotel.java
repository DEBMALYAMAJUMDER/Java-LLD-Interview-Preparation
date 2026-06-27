package hotelbooking.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class Hotel {
    private String hotelName;
    private String hotelId;
    private List<Room> rooms;
    private ConcurrentHashMap<Room, TreeMap<LocalDate, LocalDate>> bookingMap;

    public Hotel(String hotelName, String hotelId, List<Room> rooms) {
        this.hotelName = hotelName;
        this.hotelId = hotelId;
        this.rooms = rooms;
        this.bookingMap = new ConcurrentHashMap<>();
    }

    public void addBooking() {
        rooms.stream().forEach(room -> bookingMap.putIfAbsent(room, new TreeMap<>()));
    }

    public ConcurrentHashMap<Room, TreeMap<LocalDate, LocalDate>> getBookingMap() {
        return bookingMap;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelName='" + hotelName + '\'' +
                ", hotelId='" + hotelId + '\'' +
                ", rooms=" + rooms +
                ", bookingMap=" + bookingMap +
                '}';
    }
}
