package uz.pdp.my_iticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.my_iticket.payload.ApiResponse;
import uz.pdp.my_iticket.projection.TicketProjection;
import uz.pdp.my_iticket.repository.TicketRepository;
import uz.pdp.my_iticket.enums.TicketStatus;
import uz.pdp.my_iticket.utils.Constants.*;

import java.util.List;

import static uz.pdp.my_iticket.utils.Constants.EMPTY_LIST;
import static uz.pdp.my_iticket.utils.Constants.SUCCESS;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

}
