package parkinglot.factory;

import parkinglot.entity.Bike;
import parkinglot.entity.Car;
import parkinglot.entity.Truck;
import parkinglot.entity.Vehicle;

public class VehicleFactory {
    public Vehicle getVehicleFactory(String type) {
        return switch (type) {
            case "BIKE" -> new Bike();
            case "CAR" -> new Car();
            case "TRUCK" -> new Truck();
            default -> throw new RuntimeException("Unsupported Vehicle type");
        };
    }
}
