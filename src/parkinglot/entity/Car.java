package parkinglot.entity;

import java.util.List;

public class Car extends Vehicle{
    @Override
    public List<SpotType> getSupportedType() {
        return List.of(SpotType.MEDIUM,SpotType.LARGE);
    }
}
