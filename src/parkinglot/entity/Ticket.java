package parkinglot.entity;

import java.time.LocalDateTime;

public class Ticket {
    private String ticketId;
    private ParkingSpot parkingSpot;
    private LocalDateTime timeStamp;

    public Ticket(String ticketId, ParkingSpot parkingSpot, LocalDateTime timeStamp) {
        this.ticketId = ticketId;
        this.parkingSpot = parkingSpot;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", parkingSpot=" + parkingSpot +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
