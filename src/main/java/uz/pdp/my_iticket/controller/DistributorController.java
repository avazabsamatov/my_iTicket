package uz.pdp.my_iticket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.my_iticket.dto.DistributorDto;
import uz.pdp.my_iticket.model.Distributor;
import uz.pdp.my_iticket.payload.ApiResponse;
import uz.pdp.my_iticket.service.DistributorService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/distributor")
public class DistributorController {

    @Autowired
    DistributorService distributorService;

    @GetMapping
    public ResponseEntity<?> getAllDistributor() {
        List<Distributor> distributors = distributorService.getAllDistributor();
        ApiResponse apiResponse = new ApiResponse("Success", true, distributors);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDistributorById(@PathVariable UUID id) {
        Distributor distributor = distributorService.getDistributorById(id);
        return ResponseEntity.ok(new ApiResponse("Success", true, distributor));

    }

    @PostMapping
    public ResponseEntity<?> saveDistributor(@RequestPart("file") MultipartFile multipartFile,
                                             @RequestPart("json") DistributorDto distributorDto) {
        Distributor distributor1 = distributorService.saveDistributor(multipartFile, distributorDto);
        if (distributor1 != null) {
            return ResponseEntity.ok(new ApiResponse("Successfully added", true, distributor1));
        } else {
            return ResponseEntity.ok(new ApiResponse("Could not save", false, null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editDistributor(@PathVariable UUID id, @RequestBody DistributorDto distributorDto) {
        Distributor distributor = distributorService.editDistributor(distributorDto,id);
        return ResponseEntity.ok(new ApiResponse("Successfully edited", true, distributor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDistributor(@PathVariable UUID id) {
        distributorService.deleteDistributor(id);
        return ResponseEntity.ok(new ApiResponse("Successfully deleted", true, null));
    }

}
