package bookmyshow.entity;

public class Theatre {
    private Screen screen;
    private String theaterId;
    private String theaterType;

    public Theatre(String theaterId, String theaterType) {
        this.theaterId = theaterId;
        this.theaterType = theaterType;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(String theaterId) {
        this.theaterId = theaterId;
    }

    public String getTheaterType() {
        return theaterType;
    }

    public void setTheaterType(String theaterType) {
        this.theaterType = theaterType;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "screen=" + screen +
                ", theaterId='" + theaterId + '\'' +
                ", theaterType='" + theaterType + '\'' +
                '}';
    }
}
