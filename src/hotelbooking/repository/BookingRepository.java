package hotelbooking.repository;

import hotelbooking.entity.*;
import hotelbooking.exception.BookingNotFoundException;
import hotelbooking.exception.RoomNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class BookingRepository {
    private ConcurrentHashMap<String, Booking> bookingMap = new ConcurrentHashMap<>();
    private HotelRepository hotelRepository = new HotelRepository();
    AtomicInteger a = new AtomicInteger(1);

    public List<AvailableHotelRoom> getAvailableHotel(String startDate, String endDate, int capacity) {
        return hotelRepository.fetchAllHotelForBookingPossible(LocalDate.parse(startDate), LocalDate.parse(endDate), capacity);
    }

    public void addHotel(Hotel hotel) {
        hotelRepository.addHotel(hotel);
    }

    public Booking book(String startDate, String endDate, String bookingName, Hotel hotel, Room r) {
        if (hotel.getRooms().stream().noneMatch(room -> room.equals(r))) {
            throw new RoomNotFoundException("Room Not Found : " + r);
        }
        Room room = hotelRepository.bookRoom(hotel.getHotelId(), r, LocalDate.parse(startDate), LocalDate.parse(endDate));
        if (room != null) {
            BookingDate bookingDate = new BookingDate(LocalDate.parse(startDate), LocalDate.parse(endDate));
            Booking booking = new Booking("Booking - " + a.getAndIncrement(), bookingName, room, hotel, bookingDate);
            bookingMap.put(booking.getBookingId(), booking);
            return booking;
        } else {
            throw new RoomNotFoundException("Room is Not Available For Booking");
        }
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookingMap.get(bookingId);
        if (booking == null) {
            throw new BookingNotFoundException("Booking Not Found For the Request : " + bookingId);
        }
        hotelRepository.cancelRoom(booking.getBookingDate().getCheckInDate(), booking.getRoom(), booking.getHotel());
    }
}
