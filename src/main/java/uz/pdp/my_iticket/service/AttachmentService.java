package uz.pdp.my_iticket.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.my_iticket.model.Attachment;
import uz.pdp.my_iticket.model.AttachmentContent;
import uz.pdp.my_iticket.projection.AttachmentContentDataProjection;
import uz.pdp.my_iticket.projection.AttachmentProjection;
import uz.pdp.my_iticket.repository.AttachmentContentRepository;
import uz.pdp.my_iticket.repository.AttachmentRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepo;

    @Autowired
    AttachmentContentRepository attachmentContentRepo;

    public ResponseEntity<?> getAllAttachment(Integer page,Integer size) {
        PageRequest of = PageRequest.of(page, size);
        Page<AttachmentProjection> allAttachment = attachmentRepo.findAllAttachment(of);
        return ResponseEntity.ok(allAttachment);
    }

    public ResponseEntity getAttachmentContentById(UUID id) {
        Attachment attachmentById = attachmentRepo.findAttachmentById(id);
        AttachmentContentDataProjection attachmentContent = attachmentContentRepo.findAttachmentContentDataByAttachment(attachmentById);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(attachmentById.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\""+attachmentById.getFileName()+"\"")
                .body(attachmentContent.getData());
    }

    public Attachment saveAttachment(MultipartFile multipartFile) {
        try {
            Attachment attachment = attachmentRepo.save(new Attachment(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getSize()));
            AttachmentContent attachmentContent = attachmentContentRepo.save(new AttachmentContent(multipartFile.getBytes(), attachment));
            return attachment;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteAttachment(UUID id) {
        Attachment attachmentById = attachmentRepo.findAttachmentById(id);
        AttachmentContent attachmentContentByAttachment = attachmentContentRepo.findAttachmentContentByAttachment(attachmentById);
        attachmentContentRepo.deleteById(attachmentContentByAttachment.getId());
        attachmentRepo.deleteById(id);
    }
}
