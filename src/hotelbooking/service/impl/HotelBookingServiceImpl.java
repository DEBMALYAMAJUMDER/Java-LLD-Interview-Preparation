package hotelbooking.service.impl;

import hotelbooking.entity.AvailableHotelRoom;
import hotelbooking.entity.Booking;
import hotelbooking.entity.Hotel;
import hotelbooking.entity.Room;
import hotelbooking.repository.BookingRepository;
import hotelbooking.repository.HotelRepository;
import hotelbooking.service.HotelBookingService;

import java.util.List;

public class HotelBookingServiceImpl implements HotelBookingService {
    private BookingRepository bookingRepository = new BookingRepository();

    @Override
    public void registerHotel(Hotel hotel) {
        bookingRepository.addHotel(hotel);
    }

    @Override
    public List<AvailableHotelRoom> fetchAvailableHotel(String startDate, String endDate, int capacity) {
        return bookingRepository.getAvailableHotel(startDate, endDate, capacity);
    }

    @Override
    public Booking book(String startDate, String endDate, int noOfPpl, String bookingName, Hotel hotel, Room room) {
        if (room.getCapacity() < noOfPpl) {
            throw new IllegalArgumentException("Room capacity is less than No of People");
        }
        return bookingRepository.book(startDate, endDate, bookingName, hotel, room);
    }

    @Override
    public void cancelBooking(String bookingId) {
        bookingRepository.cancelBooking(bookingId);
    }
}
