package uz.pdp.my_iticket.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.my_iticket.dto.SeatDataDto;
import uz.pdp.my_iticket.dto.SeatDto;
import uz.pdp.my_iticket.model.PriceCategory;
import uz.pdp.my_iticket.model.Row;
import uz.pdp.my_iticket.model.Seat;
import uz.pdp.my_iticket.payload.ApiResponse;
import uz.pdp.my_iticket.projection.SeatAllDataProjection;
import uz.pdp.my_iticket.repository.PriceCategoryRepository;
import uz.pdp.my_iticket.repository.RowRepository;
import uz.pdp.my_iticket.repository.SeatRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static uz.pdp.my_iticket.utils.Constants.*;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    RowRepository rowRepository;

    @Autowired
    PriceCategoryRepository priceCategoryRepo;

    public ResponseEntity<?> getAllSeat(UUID rowId) {
        if (rowId == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(ERROR,false,null));
        List<SeatAllDataProjection> seats = seatRepository.findSeatsByRowId(rowId);
        return ResponseEntity.ok(new ApiResponse("Success",true,seats));
    }

    @Transactional
    public ResponseEntity<?> addSeat(UUID rowId, SeatDto seatDto) {
        if (rowId == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(ERROR,false,null));
      Row row;
      PriceCategory priceCategory;
      Integer count=0;
        try {
        row = rowRepository.findById(rowId).orElseThrow(() -> new ResourceNotFoundException("Row not found!"));
        priceCategory = priceCategoryRepo.findById(seatDto.getPriceCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Price Category not found!"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(OBJECT_NOT_FOUND,false,null));
        }

        for (Integer i = seatDto.getStartSeatNumber(); i <= (seatDto.getEndSeatNumber() != null ? seatDto.getEndSeatNumber() : seatDto.getStartSeatNumber()); i++) {
            boolean alreadyExist = seatRepository.existsSeatByNumberAndRow(i, row);
            if (!alreadyExist) {
                seatRepository.save(new Seat(i, priceCategory, row));
                count++;
            } else {
                List<UUID> seatByRowId = seatRepository.findSeatsByRowIdAndAndOrderByCreatedByIdDesc(rowId,count);
                seatRepository.deleteAllById(seatByRowId);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(EXISTS, false, null));
            }
        }
        List<SeatAllDataProjection> seats = seatRepository.findSeatsByRowId(rowId);
        return ResponseEntity.ok(new ApiResponse(SUCCESS_SAVE,true,seats));
    }

    public ResponseEntity<?> deleteSeat(UUID id) {
        if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(ERROR,false,null));
        try{
        seatRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse(SUCCESS_DELETE,true,null));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(FAILED_TO_DELETE,false,null));
        }
    }

    public ResponseEntity<?> editData(UUID id, SeatDataDto seatDataDto) {
        if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(ERROR,false,null));
      Seat seat;
      Row row;
       try{
           PriceCategory priceCategory = priceCategoryRepo.findById(seatDataDto.getPriceCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Price category not found!"));
            seat = seatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Seat not found!"));
           boolean alreadyExist = seatRepository.existsSeatByNumberAndRow(seatDataDto.getNumber(), seat.getRow());
           if (!alreadyExist){
           seat.setNumber(seatDataDto.getNumber());
           seat.setPriceCategory(priceCategory);
           }else {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(EXISTS, false, null));
           }
       }catch (Exception e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(OBJECT_NOT_FOUND,false,null));
       }
        try {
            Seat editSeat = seatRepository.save(seat);
            return ResponseEntity.ok(new ApiResponse(SUCCESS_EDIT,true,editSeat));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(FAILED_TO_EDIT,false,null));
        }
    }

    public ResponseEntity<?> getSeatById(UUID id) {
        if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(ERROR,false,null));

            SeatAllDataProjection seatsById = seatRepository.findSeatsById(id);
            if (seatsById != null) {
                return ResponseEntity.ok(new ApiResponse("Success", true, seatsById));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(OBJECT_NOT_FOUND, false, null));
            }
    }
}
