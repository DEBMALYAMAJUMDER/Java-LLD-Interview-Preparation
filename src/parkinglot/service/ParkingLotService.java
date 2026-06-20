package parkinglot.service;

import parkinglot.entity.SpotType;
import parkinglot.entity.Ticket;
import parkinglot.entity.VehicleInfo;

public interface ParkingLotService {
    Ticket parkVehicle(VehicleInfo vehicle);

    void unparkVehicle(String ticketId);

    int getAvailableSpots(SpotType type);
}
