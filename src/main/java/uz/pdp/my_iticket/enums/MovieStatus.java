package uz.pdp.my_iticket.enums;

import java.util.Arrays;
import java.util.Optional;

public enum MovieStatus {
    MOVIE_PASSED("passed"),
    MOVIE_SOON("soon"),
    MOVIE_ACTIVE("active"),
    MOVIE_DISABLED("disabled"),
    MOVIE_OTHER("other");

    final String displayName;

    MovieStatus(String displayName) {
        this.displayName = displayName;
    }

    public static MovieStatus getMovieDisplayStatus(String displayName){
        Optional<MovieStatus> movieStatusOptional = Arrays.stream(MovieStatus.values()).filter(movieStatus -> movieStatus.displayName.equals(displayName.toLowerCase())).findFirst();
        return movieStatusOptional.orElse(MovieStatus.MOVIE_OTHER);
    }
}
