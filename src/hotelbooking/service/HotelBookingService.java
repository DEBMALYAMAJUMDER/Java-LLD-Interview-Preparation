package hotelbooking.service;

import hotelbooking.entity.AvailableHotelRoom;
import hotelbooking.entity.Booking;
import hotelbooking.entity.Hotel;
import hotelbooking.entity.Room;

import java.util.List;

public interface HotelBookingService {
    void registerHotel(Hotel hotel);

    List<AvailableHotelRoom> fetchAvailableHotel(String startDate, String endDate, int capacity);

    Booking book(String startDate, String endDate, int capacity, String bookingName, Hotel hotel, Room room);

    void cancelBooking(String bookingId);
}
