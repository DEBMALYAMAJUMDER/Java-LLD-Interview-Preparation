package bookmyshow.service.impl;

import bookmyshow.entity.*;
import bookmyshow.repository.MovieBookingRepository;
import bookmyshow.service.BookingService;

import java.time.LocalDateTime;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    private MovieBookingRepository movieBookingRepository = new MovieBookingRepository();

    @Override
    public Booking bookSeats(String userId, String showId, List<String> seats) {
        try {
            return movieBookingRepository.bookSeats(userId, showId, seats);
        } catch (Exception e) {
            return new Booking(LocalDateTime.now(), BookingState.FAILED, userId);
        }
    }

    @Override
    public void cancelBooking(String bookingId) {
        movieBookingRepository.cancelBooking(bookingId);
    }

    @Override
    public List<Seat> getAvailableSeats(String showId) {
        return movieBookingRepository.getAvailableSeat(showId);
    }

    @Override
    public void addShow(Show show) {
        movieBookingRepository.addShow(show);
    }
}
