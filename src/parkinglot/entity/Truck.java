package parkinglot.entity;

import java.util.List;

public class Truck extends Vehicle{
    @Override
    public List<SpotType> getSupportedType() {
        return List.of(SpotType.LARGE);
    }
}
