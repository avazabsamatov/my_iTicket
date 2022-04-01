package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.my_iticket.model.PayType;

import java.util.UUID;

public interface PayTypeRepository extends JpaRepository<PayType, UUID> {

    PayType findPayTypeByName(String payTypeName);
}
