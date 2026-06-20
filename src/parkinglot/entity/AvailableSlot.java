package parkinglot.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AvailableSlot {
    BlockingQueue<ParkingSpot> smallSpot;
    BlockingQueue<ParkingSpot> mediumSpot;
    BlockingQueue<ParkingSpot> largeSpot;
    BookingLot bookingLot = new BookingLot();

    public BookingLot getBookingLot() {
        return bookingLot;
    }

    public AvailableSlot(int small, int medium, int large) {
        smallSpot = createSpot(small, SpotType.SMALL);
        mediumSpot = createSpot(medium, SpotType.MEDIUM);
        largeSpot = createSpot(large, SpotType.LARGE);
        bookingLot.init(SpotType.SMALL, smallSpot);
        bookingLot.init(SpotType.MEDIUM, mediumSpot);
        bookingLot.init(SpotType.LARGE, largeSpot);
    }

    private BlockingQueue<ParkingSpot> createSpot(int count, SpotType type) {
        List<ParkingSpot> spots = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            spots.add(new ParkingSpot(i + 1, type, false));
        }
        return new LinkedBlockingQueue<>(spots);
    }
}
