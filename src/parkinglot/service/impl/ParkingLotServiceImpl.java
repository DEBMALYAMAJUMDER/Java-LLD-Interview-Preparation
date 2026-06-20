package parkinglot.service.impl;

import parkinglot.entity.*;
import parkinglot.factory.VehicleFactory;
import parkinglot.service.ParkingLotService;

public class ParkingLotServiceImpl implements ParkingLotService {
    private VehicleFactory vehicleFactory = new VehicleFactory();
    private final BookingLot bookingLot ;

    public ParkingLotServiceImpl(BookingLot bookingLot) {
        this.bookingLot = bookingLot;
    }

    @Override
    public Ticket parkVehicle(VehicleInfo info) {
        Vehicle vehicle = vehicleFactory.getVehicleFactory(info.getVehicleType());
        var spots = vehicle.getSupportedType();
        for (SpotType type : spots) {
            var ticket = bookingLot.acquire(type);
            if (null != ticket) {
                return ticket;
            }
        }
        System.out.println("Parking is Full,Regret");
        return null;
    }

    @Override
    public void unparkVehicle(String ticketId) {
        bookingLot.release(ticketId);
    }

    @Override
    public int getAvailableSpots(SpotType type) {
        return bookingLot.getAvailableSlot(type);
    }
}
