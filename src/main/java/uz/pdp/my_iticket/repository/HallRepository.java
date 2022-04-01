package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.my_iticket.model.Hall;
import uz.pdp.my_iticket.projection.HallAllDataProjection;

import java.util.UUID;

public interface HallRepository extends JpaRepository<Hall, UUID> {

    @Query(value = "select cast(h.id as varchar) as id," +
            "h.name as name," +
            "h.vip_additional_fee_in_percent as vipAdditionalFeeInPercent from halls h where h.id = :id",nativeQuery = true)
HallAllDataProjection findHallsById(UUID id);
}
