package bookmyshow.repository;

import bookmyshow.entity.*;
import bookmyshow.exception.BookingNotFoundException;
import bookmyshow.exception.DuplicateShowException;
import bookmyshow.exception.InvalidShowException;
import bookmyshow.exception.SeatUnavailableException;
import bookmyshow.service.ValidationService;
import bookmyshow.service.impl.ValidationServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieBookingRepository {
    private ConcurrentHashMap<String, Show> showMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Show> timeMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Booking> bookingMap = new ConcurrentHashMap<>();
    private ValidationService validationService = new ValidationServiceImpl();
    private AtomicInteger counter = new AtomicInteger(1);

    public void addShow(Show show) {
        if (!validationService.validateShow(show)) {
            throw new InvalidShowException("show is invalid" + show);
        }
        var time = show.getTime();
        Show existingShow = timeMap.get(time + "_" + show.getScreen().getScreenId());
        if (existingShow != null) {
            throw new DuplicateShowException("A Show in same screen exists : " + show);
        }
        var seats = show.getScreen().getSeat().stream().map(s-> new Seat(s.getSeat())).toList();
        show.setSeats(seats);
        showMap.put(show.getShowId(), show);
        timeMap.put(show.getTime() + "_" + show.getScreen().getScreenId(), show);
        System.out.println("Show is Successfully added to system : " + show);
    }

    public List<Seat> getAvailableSeat(String showId) {
        var show = showMap.get(showId);
        if (show == null) {
            throw new InvalidShowException("Show does't exists : showId = " + showId);
        }
        return show.getAvailableSeats();
    }

    public void cancelBooking(String bookingId) {
        var booking = bookingMap.remove(bookingId);
        if (booking == null) {
            throw new BookingNotFoundException("Booking is not Found for BookingId : " + bookingId);
        }
        if (BookingState.COMPLETED == booking.getBookingState()) {
            var show = booking.getShow();
            var updatedMap = booking.getBookedSeat().stream().collect(Collectors.toMap(Seat::getSeat, Function.identity()));
            show.getSeats().forEach(s -> {
                if (updatedMap.containsKey(s.getSeat()) && !s.isAvailable()) {
                    s.setAvailable(true);
                }
            });
        } else {
            throw new IllegalStateException("State is Illegal");
        }
    }

    public Booking bookSeats(String userId, String showId, List<String> seats) {
        if (showId.isBlank()) {
            throw new IllegalArgumentException("Invalid showid : " + showId);
        }
        Show show = showMap.get(showId);
        if (show == null) {
            throw new InvalidShowException("Show doesn't exists : showId = " + showId);
        }
        List<Seat> bookedSeats = new ArrayList<>();
        var toBeUpdated = seats.stream().collect(Collectors.toMap(Function.identity(), Function.identity()));
        show.getSeats().forEach(s -> {
            if (toBeUpdated.containsKey(s.getSeat())) {
                if (s.isAvailable()) {
                    s.setAvailable(false);
                    bookedSeats.add(s);
                } else {
                    bookedSeats.forEach(s1 -> s1.setAvailable(true));
                    throw new SeatUnavailableException("Seat is Not available");
                }
            }
        });
        Booking booking = new Booking("Booking-" + counter.getAndIncrement(), LocalDateTime.now(), BookingState.COMPLETED, userId, show, bookedSeats);
        System.out.println(booking);
        bookingMap.put(booking.getBookingId(), booking);
        return booking;
    }
}
