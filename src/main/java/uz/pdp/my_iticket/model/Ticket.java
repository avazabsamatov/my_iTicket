package uz.pdp.my_iticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.my_iticket.enums.TicketStatus;
import uz.pdp.my_iticket.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tickets")
public class Ticket extends AbsEntity {

    private String qrCode;

    private double price;

    @ManyToOne
    private MovieSession movieSession;

    @ManyToOne
    private Seat seat;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

}
