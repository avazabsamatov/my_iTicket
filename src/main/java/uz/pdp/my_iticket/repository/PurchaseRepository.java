package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.my_iticket.model.PurchaseHistory;

import java.util.UUID;


public interface PurchaseRepository extends JpaRepository<PurchaseHistory, UUID> {

    @Query(nativeQuery = true,value = "select p.* from purchase_histories p " +
            "join pay_types pt on pt.id = p.pay_type_id " +
            "join attachments a on a.id = pt.logo_id " +
            "join purchase_histories_tickets pht on p.id = pht.purchase_history_id " +
            "join tickets t on t.id = pht.ticket_id where t.id=:ticketId and p.purchase_status='PURCHASE_PAID'")
    PurchaseHistory findPurchaseHistoryByTickets(UUID ticketId);

}
