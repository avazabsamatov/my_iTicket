package uz.pdp.my_iticket.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface HallSessionProjection {
    UUID getId();

//    UUID getMovieAnnouncementId();

    String getHallName();

//    LocalDate getStartDate();

    @Value("#{@movieSessionRepository.getHallTime(target.id,target.movieAnnouncementId,target.startDate)}")
    List<TimeSessionProjection> getTimes();
}
