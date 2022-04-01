package uz.pdp.my_iticket.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.my_iticket.dto.CastDto;
import uz.pdp.my_iticket.enums.CastType;
import uz.pdp.my_iticket.model.Attachment;
import uz.pdp.my_iticket.model.AttachmentContent;
import uz.pdp.my_iticket.model.Cast;
import uz.pdp.my_iticket.projection.CastProjection;
import uz.pdp.my_iticket.repository.AttachmentContentRepository;
import uz.pdp.my_iticket.repository.AttachmentRepository;
import uz.pdp.my_iticket.repository.CastRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CastService {

    @Autowired
    CastRepository castRepository;

    @Autowired
    AttachmentRepository attachmentRepo;

    @Autowired
    AttachmentContentRepository attachmentContentRepo;

    public ResponseEntity<?> getAllCast(Integer page,Integer size) {
        PageRequest of = PageRequest.of(page, size);
        Page<CastProjection> allCast = castRepository.getAllCast(of);
        return ResponseEntity.ok(allCast);
    }

    public Cast getCastById(UUID id) {
        Optional<Cast> byId = castRepository.findById(id);
        return byId.get();
    }

    public Cast addCast(MultipartFile file, CastDto castDto) {
        try {
            Attachment attachment = attachmentRepo.save(new Attachment(file.getOriginalFilename(), file.getContentType(), file.getSize()));
            AttachmentContent save = attachmentContentRepo.save(new AttachmentContent(file.getBytes(), attachment));
           return castRepository.save(new Cast(castDto.getFullName(),attachment, CastType.getCastDisplayType(castDto.getCastType())));
        } catch (Exception e){
            return null;
        }
    }

    public void deleteCast(UUID id) {
        Optional<Cast> byId = castRepository.findById(id);
        Attachment attachment = attachmentRepo.findAttachmentById(byId.get().getPhoto().getId());
        AttachmentContent contentByAttachment = attachmentContentRepo.findAttachmentContentByAttachment(attachment);
        attachmentContentRepo.deleteById(contentByAttachment.getId());
        castRepository.deleteById(id);
    }

    public Cast edit(UUID id, CastDto castDto) {
        try {
        Optional<Cast> byId = castRepository.findById(id);
        Cast cast=byId.get();
        cast.setFullName(castDto.getFullName());
        cast.setCastType(CastType.getCastDisplayType(castDto.getCastType()));
         return castRepository.save(cast);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
