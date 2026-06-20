package bookmyshow;

import bookmyshow.entity.*;
import bookmyshow.service.BookingService;
import bookmyshow.service.impl.BookingServiceImpl;

import java.util.List;

public class MovieBookingApplication {
    public static void main(String[] args) {
        BookingService bookingService = new BookingServiceImpl();

        Movie interstellar =
                new Movie("M1", "Interstellar");

        Movie inception =
                new Movie("M2", "Inception");

        Screen screen1 =
                new Screen("S1", "Screen1");

        screen1.addSeat(new Seat("A1"));
        screen1.addSeat(new Seat("A2"));
        screen1.addSeat(new Seat("A3"));

        Show show1 =
                new Show(
                        "SHOW1",
                        interstellar,
                        screen1,
                        "10AM");

        Show show2 =
                new Show(
                        "SHOW2",
                        inception,
                        screen1,
                        "2PM");

        bookingService.addShow(show1);
        bookingService.addShow(show2);

        System.out.println("===== BEFORE BOOKING =====");

        System.out.println(
                "SHOW1 Available Seats : "
                        + bookingService.getAvailableSeats("SHOW1"));

        System.out.println(
                "SHOW2 Available Seats : "
                        + bookingService.getAvailableSeats("SHOW2"));

        bookingService.bookSeats(
                "USER1",
                "SHOW1",
                List.of("A1"));

        System.out.println("\n===== AFTER BOOKING A1 FOR SHOW1 =====");

        System.out.println(
                "SHOW1 Available Seats : "
                        + bookingService.getAvailableSeats("SHOW1"));

        System.out.println(
                "SHOW2 Available Seats : "
                        + bookingService.getAvailableSeats("SHOW2"));
    }
}
