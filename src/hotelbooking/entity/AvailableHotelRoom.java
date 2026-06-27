package hotelbooking.entity;

public class AvailableHotelRoom {
    private Hotel hotel;
    private Room room;

    public AvailableHotelRoom(Hotel hotel, Room room) {
        this.hotel = hotel;
        this.room = room;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "AvailableHotelRoom{" +
                "hotel=" + hotel +
                ", room=" + room +
                '}';
    }
}
