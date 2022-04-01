package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.my_iticket.model.CashBox;

import java.util.UUID;

@RepositoryRestResource(path = "cash-box",collectionResourceRel = "cashBox")
public interface CashBoxRepository extends JpaRepository<CashBox, UUID> {

}
