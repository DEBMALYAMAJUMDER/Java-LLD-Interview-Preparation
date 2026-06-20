package bookmyshow.entity;

import java.util.ArrayList;
import java.util.List;

public class Show {
    private String showId;
    private Movie movie;
    private Screen screen;
    private String time;
    private List<Seat> seats;

    public Show(String showId, Movie movie, Screen screen, String time) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.time = time;
    }
    public List<Seat> getAvailableSeats() {
        return seats.stream()
                .filter(Seat::isAvailable)
                .toList();
    }
    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = new ArrayList<>(seats);
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Show{" +
                "showId='" + showId + '\'' +
                ", movie=" + movie +
                ", screen=" + screen +
                ", time='" + time + '\'' +
                ", seats=" + seats +
                '}';
    }
}
