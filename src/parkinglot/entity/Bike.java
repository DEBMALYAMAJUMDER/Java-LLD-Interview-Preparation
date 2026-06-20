package parkinglot.entity;

import java.util.List;

public class Bike extends Vehicle{
    @Override
    public List<SpotType> getSupportedType() {
        return List.of(SpotType.SMALL,SpotType.MEDIUM,SpotType.LARGE);
    }
}
