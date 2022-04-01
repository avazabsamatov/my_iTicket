package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.my_iticket.model.SessionAdditionalFeeInPercent;

import java.time.LocalTime;
import java.util.UUID;

@RepositoryRestResource(path = "sessionAdditionalFeeInPercent",collectionResourceRel = "sessionAdditionalFeeInPercent")
public interface SessionAdditionalFeeInPercentRepository extends JpaRepository<SessionAdditionalFeeInPercent, UUID> {

    @Query(nativeQuery = true,value = "SELECT session_additional_fee_in_percent from session_additional_fee_in_percent safip where safip.time <= :time order by time desc limit 1")
    double checkTime(LocalTime time);
}
