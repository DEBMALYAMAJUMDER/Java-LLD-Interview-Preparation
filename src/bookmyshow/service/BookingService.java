package bookmyshow.service;

import bookmyshow.entity.Booking;
import bookmyshow.entity.Seat;
import bookmyshow.entity.Show;

import java.util.List;

public interface BookingService {
    Booking bookSeats(String userId, String showId, List<String> seats);
    void cancelBooking(String bookingId);
    List<Seat> getAvailableSeats(String showId);
    void addShow(Show show);
}
