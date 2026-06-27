package hotelbooking.entity;

import java.time.LocalDate;

public class Booking {
    private String bookingId;
    private String bookingName;
    private Room room;
    private Hotel hotel;
    private BookingDate bookingDate;

    public Booking(String bookingId, String bookingName, Room room, Hotel hotel, BookingDate bookingDate) {
        this.bookingId = bookingId;
        this.bookingName = bookingName;
        this.room = room;
        this.hotel = hotel;
        this.bookingDate = bookingDate;
    }

    public Booking(String bookingId, String bookingName, Room room, Hotel hotel) {
        this.bookingId = bookingId;
        this.bookingName = bookingName;
        this.room = room;
        this.hotel = hotel;
    }

    public String getBookingId() {
        return bookingId;
    }

    public BookingDate getBookingDate() {
        return bookingDate;
    }

    public String getBookingName() {
        return bookingName;
    }

    public Room getRoom() {
        return room;
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", bookingName='" + bookingName + '\'' +
                ", room=" + room +
                ", hotel=" + hotel +
                ", bookingDate=" + bookingDate +
                '}';
    }
}
