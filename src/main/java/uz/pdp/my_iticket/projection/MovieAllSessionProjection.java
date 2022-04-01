package uz.pdp.my_iticket.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MovieAllSessionProjection {
    UUID getMovieAnnouncementId();

    UUID getMovieId();

    UUID getMoviePosterImageId();

    String getMovieTitle();

    LocalDate getStartDate();

    @Value("#{@movieSessionRepository.getSessionHall(target.movieAnnouncementId,target.startDate)}")
    List<HallSessionProjection> getHalls();
}
