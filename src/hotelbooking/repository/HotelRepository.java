package hotelbooking.repository;

import hotelbooking.entity.AvailableHotelRoom;
import hotelbooking.entity.Hotel;
import hotelbooking.entity.Room;
import hotelbooking.exception.HotelNotFoundException;
import hotelbooking.exception.RoomNotFoundException;
import hotelbooking.service.RoomBookingService;
import hotelbooking.service.impl.RoomBookingServiceImpl;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class HotelRepository {
    private ConcurrentHashMap<String, Hotel> hotelMap = new ConcurrentHashMap<>();
    private RoomBookingService roomBookingService = new RoomBookingServiceImpl();

    public List<AvailableHotelRoom> fetchAllHotelForBookingPossible(LocalDate startDate, LocalDate endDate, int capacity) {
        List<Hotel> hotels = new ArrayList<>(hotelMap.values());
        return hotels.stream().flatMap(hotel -> hotel.getRooms().stream()
                .filter(room -> room.getCapacity() >= capacity
                        && roomBookingService.availableForTheDate(startDate, endDate, hotel.getBookingMap().get(room)))
                .map(room -> new AvailableHotelRoom(hotel, room))).toList();
    }

    public PriorityBlockingQueue<Room> roomWithCapacity(int capacity, String hotelId) {
        Hotel hotel = hotelMap.get(hotelId);
        if (hotel == null) {
            throw new HotelNotFoundException("Hotel is Not Registered");
        }
        PriorityBlockingQueue<Room> pq = new PriorityBlockingQueue<>(1000, Comparator.comparingInt(Room::getCapacity));
        List<Room> avlRooms = hotel.getRooms().stream().filter(room -> room.getCapacity() >= capacity).toList();
        pq.addAll(avlRooms);
        return pq;
    }

    public Room bookRoom(String hotelId, Room room, LocalDate startDate, LocalDate endDate) {
        Hotel hotel = hotelMap.get(hotelId);
        if (hotel == null) {
            throw new HotelNotFoundException("Hotel is Not Registered");
        }
        ConcurrentHashMap<Room, TreeMap<LocalDate, LocalDate>> bookingMap = hotel.getBookingMap();
        synchronized (room.getRoomNo().intern()) {
            if (roomBookingService.availableForTheDate(startDate, endDate, bookingMap.get(room))) {
                bookingMap.computeIfAbsent(room, r -> new TreeMap<>()).put(startDate, endDate);
                return room;
            }
        }
        return null;
    }

    public void cancelRoom(LocalDate startDate, Room room, Hotel hotel) {
        ConcurrentHashMap<Room, TreeMap<LocalDate, LocalDate>> bookingMap = hotel.getBookingMap();
        bookingMap.get(room).remove(startDate);
        System.out.println("Booking Cancelled , Room : " + room);
    }

    public void addHotel(Hotel hotel) {
        hotelMap.put(hotel.getHotelId(), hotel);
        hotel.addBooking();
        System.out.println("Hotel is Added SuccessFully,HotelMap : " + hotelMap);
    }
}
