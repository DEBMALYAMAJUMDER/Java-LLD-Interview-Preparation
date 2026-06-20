package bookmyshow.entity;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private String screenId;
    private String screenName;
    private List<Seat> seats;

    public Screen(String screenId, String screenName) {
        this.screenId = screenId;
        this.screenName = screenName;
        seats = new ArrayList<>();
    }

    public void addSeat(Seat s) {
        seats.add(s);
        System.out.println("New Seat Added : " + s);
    }


    public String getScreenId() {
        return screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public List<Seat> getSeat() {
        return seats;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "screenId='" + screenId + '\'' +
                ", screenName='" + screenName + '\'' +
                ", seats=" + seats +
                '}';
    }
}
