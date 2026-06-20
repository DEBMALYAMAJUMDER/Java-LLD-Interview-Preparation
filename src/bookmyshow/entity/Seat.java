package bookmyshow.entity;

import java.util.Objects;

public class Seat {
    private String seat;
    private boolean isAvailable;
    private SeatType type; // Future Type
    public Seat(String seat) {
        this.seat = seat;
        isAvailable = true;
    }

    public String getSeat() {
        return seat;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seat='" + seat + '\'' +
                ", isAvailable=" + isAvailable +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Seat seat1)) return false;
        return isAvailable == seat1.isAvailable && Objects.equals(seat, seat1.seat) && Objects.equals(type, seat1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seat, isAvailable, type);
    }
}
