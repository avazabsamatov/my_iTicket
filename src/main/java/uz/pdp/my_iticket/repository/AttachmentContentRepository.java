package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.my_iticket.model.Attachment;
import uz.pdp.my_iticket.model.AttachmentContent;
import uz.pdp.my_iticket.projection.AttachmentContentDataProjection;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
//    @Query(nativeQuery = true,value = "select a from attachment_contents a where a.attachment_id = :id")
    AttachmentContent findAttachmentContentByAttachmentId(UUID id);
    AttachmentContent findAttachmentContentByAttachment(Attachment attachment);
    AttachmentContentDataProjection findAttachmentContentDataByAttachment(Attachment attachment);

}
