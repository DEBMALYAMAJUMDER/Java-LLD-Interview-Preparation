package bookmyshow.service.impl;

import bookmyshow.entity.Show;
import bookmyshow.service.ValidationService;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validateShow(Show show) {
        if (show.getShowId().isBlank()) {
            return false;
        }
        if (show.getMovie() == null || (show.getMovie().getMovieName().isBlank() || show.getMovie().getMovieId().isBlank())) {
            return false;
        }
        if (show.getScreen() == null || (show.getScreen().getScreenId().isBlank() || show.getScreen().getScreenName().isBlank())) {
            return false;
        }
        return !show.getTime().isBlank();
    }
}
