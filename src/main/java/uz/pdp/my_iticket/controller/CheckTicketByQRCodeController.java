package uz.pdp.my_iticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.my_iticket.service.CheckTicketByQRCodeService;

import java.util.UUID;

@RestController
@RequestMapping("/api/check-ticket")
public class CheckTicketByQRCodeController {

    @Autowired
    CheckTicketByQRCodeService checkTicketByQRCodeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> checkTicketByQRCode(@PathVariable UUID id){
        return checkTicketByQRCodeService.checkTicket(id);
    }

}
