package parkinglot;

import parkinglot.entity.AvailableSlot;
import parkinglot.entity.BookingLot;
import parkinglot.entity.SpotType;
import parkinglot.entity.VehicleInfo;
import parkinglot.service.ParkingLotService;
import parkinglot.service.impl.ParkingLotServiceImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ParkingLotMain {
    public static void main(String[] args) {
        AvailableSlot availableSlot = new AvailableSlot(2, 2, 1);
        BookingLot bookingLot = availableSlot.getBookingLot();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(bookingLot);
        System.out.println(parkingLotService.parkVehicle(new VehicleInfo("CAR", 1)));
        System.out.println(parkingLotService.parkVehicle(new VehicleInfo("CAR", 2)));
        System.out.println(parkingLotService.parkVehicle(new VehicleInfo("CAR", 3)));
        System.out.println(parkingLotService.parkVehicle(new VehicleInfo("CAR", 4)));
        System.out.println(parkingLotService.parkVehicle(new VehicleInfo("BIKE", 5)));
        parkingLotService.unparkVehicle("Ticket-1");
        parkingLotService.unparkVehicle("Ticket-1");
        parkingLotService.unparkVehicle("Ticket-2");
        System.out.println(parkingLotService.parkVehicle(new VehicleInfo("TRUCK", 6)));
        System.out.println("Small : " + bookingLot.getAvailableSlot(SpotType.SMALL) + " Medium : " + bookingLot.getAvailableSlot(SpotType.MEDIUM) + " Large : " +
                bookingLot.getAvailableSlot(SpotType.LARGE));
    }
}