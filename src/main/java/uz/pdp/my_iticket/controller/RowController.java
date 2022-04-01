package uz.pdp.my_iticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.my_iticket.dto.RowDto;
import uz.pdp.my_iticket.service.RowService;

import java.util.UUID;

@RestController
@RequestMapping("/api/row")
public class RowController {

    @Autowired
    RowService rowService;

    @GetMapping
    public ResponseEntity<?> getAllRows(@RequestParam(value = "hallId",required = false)UUID id){
       return rowService.getAllRows(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRowById(
            @PathVariable UUID id){
        return rowService.getRowById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editRow(@PathVariable UUID id, RowDto rowDto){
        return rowService.editRow(id,rowDto);
    }

    @PostMapping
    public ResponseEntity<?> addRow(@RequestParam(value = "hallId",required = false) UUID id, @RequestBody RowDto rowDto){
        return rowService.addRow(id,rowDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRow(@PathVariable UUID id){
        return rowService.deleteRow(id);
    }
}
