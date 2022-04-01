package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.my_iticket.model.RefundChargeFee;

import java.util.UUID;

@RepositoryRestResource(path = "refund-charge-fee",collectionResourceRel = "refundChargeFee")
public interface RefundChargeFeeRepository extends JpaRepository<RefundChargeFee, UUID> {
    @Query(nativeQuery = true,value = "SELECT charge_fee_in_percentage from refund_charge_fee where interval_in_minutes <= :interval order by interval_in_minutes desc limit 1")
    Double getPercent(Integer interval);
}
