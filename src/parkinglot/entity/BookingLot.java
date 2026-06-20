package parkinglot.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BookingLot {
    private Map<SpotType, BlockingQueue<ParkingSpot>> avlSpt = new HashMap<>();
    private Map<String, ParkingSpot> ticketMap = new HashMap<>();
    int a = 1;

    public void init(SpotType type, BlockingQueue<ParkingSpot> spot) {
        avlSpt.put(type, spot);
    }

    public Ticket acquire(SpotType type) {
        if (!avlSpt.get(type).isEmpty()) {
            var tickId = "Ticket-" + a++;
            var spot = avlSpt.get(type).poll();
            if (spot == null) {
                return null;
            }
            spot.setOccupied(true);
            ticketMap.put(tickId, spot);
            System.out.println("Acquiring spot : " + spot);
            return new Ticket(tickId, spot, LocalDateTime.now());
        }
        return null;
    }

    public ParkingSpot release(String ticketId) {
        var spot = ticketMap.remove(ticketId);
        if (null == spot) {
            System.out.println("Invalid Ticket : " + ticketId);
            return null;
        }
        spot.setOccupied(false);
        avlSpt.get(spot.getType()).add(spot);
        System.out.println("Releasing spot : " + spot);
        return spot;
    }

    public int getAvailableSlot(SpotType type) {
        return avlSpt.get(type).size();
    }
}
