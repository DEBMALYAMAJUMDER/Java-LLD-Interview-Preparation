package parkinglot.entity;

public class ParkingSpot {
    private final int id;

    public ParkingSpot(int id, SpotType type, boolean occupied) {
        this.id = id;
        this.type = type;
        this.occupied = occupied;
    }

    private final SpotType type;
    private boolean occupied;

    public int getId() {
        return id;
    }

    public SpotType getType() {
        return type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", type=" + type +
                ", occupied=" + occupied +
                '}';
    }
}
