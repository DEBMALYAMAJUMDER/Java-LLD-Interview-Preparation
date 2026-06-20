package bookmyshow.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Booking {
    private String bookingId;
    private LocalDateTime localDateTime;
    private BookingState bookingState;
    private String userId;
    private Show show;
    private List<Seat> bookedSeat;

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", localDateTime=" + localDateTime +
                ", bookingState=" + bookingState +
                ", userId='" + userId + '\'' +
                ", show=" + show +
                ", bookedSeat=" + bookedSeat +
                '}';
    }

    public Booking(String bookingId, LocalDateTime localDateTime, BookingState bookingState, String userId, Show show, List<Seat> bookedSeat) {
        this.bookingId = bookingId;
        this.localDateTime = localDateTime;
        this.bookingState = bookingState;
        this.userId = userId;
        this.show = show;
        this.bookedSeat = bookedSeat;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Booking(LocalDateTime localDateTime, BookingState bookingState, String userId) {
        this.localDateTime = localDateTime;
        this.bookingState = bookingState;
        this.userId = userId;
    }

    public Booking(String bookingId, LocalDateTime localDateTime, BookingState bookingState, Show show, List<Seat> bookedSeat) {
        this.bookingId = bookingId;
        this.localDateTime = localDateTime;
        this.bookingState = bookingState;
        this.show = show;
        this.bookedSeat = bookedSeat;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public BookingState getBookingState() {
        return bookingState;
    }

    public void setBookingState(BookingState bookingState) {
        this.bookingState = bookingState;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<Seat> getBookedSeat() {
        return bookedSeat;
    }

    public void setBookedSeat(List<Seat> bookedSeat) {
        this.bookedSeat = bookedSeat;
    }

}
