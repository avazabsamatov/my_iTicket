package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.my_iticket.model.ExtraTimeBetweenSessions;

import java.util.UUID;

@RepositoryRestResource(path = "extraTimeBetweenSessionsRepository",collectionResourceRel = "extraTimeBetweenSessionsRepository")
public interface ExtraTimeBetweenSessionsRepository extends JpaRepository<ExtraTimeBetweenSessions, UUID> {

    @Query(nativeQuery = true,value = "SELECT extra_time from extra_time_between_sessions order by updated_at limit 1")
    Integer findFirst();
}
