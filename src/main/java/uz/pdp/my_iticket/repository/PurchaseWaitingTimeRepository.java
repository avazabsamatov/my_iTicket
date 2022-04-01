package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.my_iticket.model.PurchaseWaitingTime;

import java.util.UUID;

@RepositoryRestResource(path = "purchase-waiting-time",collectionResourceRel = "purchaseWaitingTime")
public interface PurchaseWaitingTimeRepository extends JpaRepository<PurchaseWaitingTime, UUID> {

    @Query(nativeQuery = true, value = "SELECT time_in_min from purchase_waiting_time  order by updated_at desc limit 1")
    Integer findFirstByUpdatedAt();
}
