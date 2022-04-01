package uz.pdp.my_iticket.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

public interface DistributorProjection {
    UUID getId();

    String getName();

    String getDescription();

//    @Value("#{@attachmentRepository.getAttachmentByDistributorId(target.id)}")
//    AttachmentProjection getLogo();
}
