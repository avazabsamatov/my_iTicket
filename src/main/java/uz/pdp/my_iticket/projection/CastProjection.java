package uz.pdp.my_iticket.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface CastProjection {
    UUID getId();

    String getFullName();

    String getCastType();

    @Value("#{@attachmentRepository.findAllAttachmentById(target.id)}")
    List<AttachmentProjection> getAttachment();
}
