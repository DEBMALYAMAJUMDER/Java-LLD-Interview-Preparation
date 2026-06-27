package hotelbooking.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {
    private String roomNo;
    private int capacity;
    private double price;
    private RoomType roomType; // Future Use

    public Room(String roomNo, int capacity, double price, RoomType roomType) {
        this.roomNo = roomNo;
        this.capacity = capacity;
        this.price = price;
        this.roomType = roomType;
    }


    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Room room)) return false;
        return capacity == room.capacity && Objects.equals(roomNo, room.roomNo) && roomType == room.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNo, capacity, price, roomType);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNo='" + roomNo + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", roomType=" + roomType +
                '}';
    }
}
