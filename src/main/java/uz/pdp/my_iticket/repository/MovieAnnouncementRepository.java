package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.my_iticket.model.MovieAnnouncement;

import java.util.UUID;

public interface MovieAnnouncementRepository extends JpaRepository<MovieAnnouncement, UUID> {
}
