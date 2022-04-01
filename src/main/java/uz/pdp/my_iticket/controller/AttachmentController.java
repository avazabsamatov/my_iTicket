package uz.pdp.my_iticket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.my_iticket.model.Attachment;
import uz.pdp.my_iticket.payload.ApiResponse;
import uz.pdp.my_iticket.projection.AttachmentProjection;
import uz.pdp.my_iticket.service.AttachmentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @GetMapping
    public ResponseEntity<?> getAllAttachment(
            @RequestParam (name = "page",defaultValue = "0") Integer page,
            @RequestParam (name = "size",defaultValue = "2") Integer size){
      return ResponseEntity.ok( attachmentService.getAllAttachment(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAttachmentContentById(@PathVariable UUID id){
        return attachmentService.getAttachmentContentById(id);
    }

    @PostMapping
    public ResponseEntity<?> saveAttachment(@RequestPart("file")MultipartFile multipartFile){
       Attachment attachment = attachmentService.saveAttachment(multipartFile);
        if (attachment != null) {
            return ResponseEntity.ok(new ApiResponse("Successfully added", true, attachment));
        } else {
            return ResponseEntity.ok(new ApiResponse("Could not save", false, null));
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttachment(@PathVariable UUID id){
        attachmentService.deleteAttachment(id);
        return ResponseEntity.ok(new ApiResponse("Successfully deleted", true, null));
    }
}
