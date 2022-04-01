package uz.pdp.my_iticket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.my_iticket.dto.CastDto;
import uz.pdp.my_iticket.model.Cast;
import uz.pdp.my_iticket.payload.ApiResponse;
import uz.pdp.my_iticket.service.CastService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cast")
public class CastController {

    @Autowired
    CastService castService;

    @GetMapping
    public ResponseEntity<?> getAllCast(@RequestParam (name = "page",defaultValue = "0") Integer page,
                                        @RequestParam (name = "size",defaultValue = "2") Integer size){
        return ResponseEntity.ok(new ApiResponse("success",true,castService.getAllCast(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCastById(@PathVariable UUID id){
        Cast cast = castService.getCastById(id);
        return ResponseEntity.ok(new ApiResponse("succes",true,cast));
    }

    @PostMapping
    public ResponseEntity<?> addCast(
            @RequestPart(name = "file")MultipartFile file,
            @RequestPart(name = "json") CastDto castDto
            ){
        Cast addCast = castService.addCast(file,castDto);
        if (addCast != null) {
        return ResponseEntity.ok(new ApiResponse("succes",true,addCast));
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Error",false, null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCast(@PathVariable UUID id, @RequestBody CastDto castDto){
       Cast cast =  castService.edit(id,castDto);
        if (cast != null) {
            return ResponseEntity.ok(new ApiResponse("Successfully edited",true,cast));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Error", false, null));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCast(@PathVariable UUID id){
        castService.deleteCast(id);
        return ResponseEntity.ok(new ApiResponse("Successfully deleted",true,null));
    }
}
