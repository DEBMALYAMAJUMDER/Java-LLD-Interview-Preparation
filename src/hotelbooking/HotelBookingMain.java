package hotelbooking;

import hotelbooking.entity.Booking;
import hotelbooking.entity.Hotel;
import hotelbooking.entity.Room;
import hotelbooking.entity.RoomType;
import hotelbooking.service.HotelBookingService;
import hotelbooking.service.impl.HotelBookingServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingMain {
    public static void main(String[] args) {
        Room r1 = new Room("101", 2, 2500, RoomType.DELUXE);
        Room r2 = new Room("102", 4, 4500, RoomType.REGULAR);
        Room r3 = new Room("201", 2, 2200, RoomType.DELUXE);
        Room r4 = new Room("202", 6, 7000, RoomType.REGULAR);
        HotelBookingService hotelBookingService = new HotelBookingServiceImpl();
        Hotel taj = new Hotel(
                "Taj", "H1",
                new ArrayList<>(List.of(
                        r1,
                        r2
                ))
        );

        Hotel oberoi = new Hotel(
                "Oberoi", "H2",
                new ArrayList<>(List.of(
                        r3, r4
                ))
        );

        hotelBookingService.registerHotel(taj);
        hotelBookingService.registerHotel(oberoi);

        System.out.println("Available Hotels : " +
                hotelBookingService.fetchAvailableHotel(
                        "2026-07-01",
                        "2026-07-03",
                        2
                )
        );
        try {
            System.out.println("Booking Status : " +
                    hotelBookingService.book(
                            "2026-07-01", "2026-07-03",
                            2, "A", taj, r1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        try {
            System.out.println("Booking Status : " +
                    hotelBookingService.book(
                            "2026-07-01", "2026-07-03",
                            2, "B", taj, r1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Available Hotels : " +
                hotelBookingService.fetchAvailableHotel(
                        "2026-07-03",
                        "2026-07-07",
                        2
                )
        );
        System.out.println("Booked Hotel : " +
                hotelBookingService.book(
                        "2026-07-04",
                        "2026-07-06",
                        2,
                        "Rahul",
                        taj, r2
                ));
        System.out.println("Booked Hotel : " +
                hotelBookingService.book(
                        "2026-07-05",
                        "2026-07-06",
                        2,
                        "Raj",
                        taj, r1
                ));
        System.out.println("Available Hotels : " +
                hotelBookingService.fetchAvailableHotel(
                        "2026-07-01",
                        "2026-07-04",
                        2
                )
        );
        System.out.println("Booked Hotel : " +
                hotelBookingService.book(
                        "2026-07-03",
                        "2026-07-04",
                        3,
                        "Subhadip",
                        taj, r1
                ));
        System.out.println(LocalDate.parse("2026-07-03").isBefore(LocalDate.parse("2026-07-03")));
    }
}

