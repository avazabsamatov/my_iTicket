package uz.pdp.my_iticket.projection;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public interface TicketProjection {
    UUID getId();

    Double getPrice();

    String getHallName();

    Integer getRowNumber();

    Integer getSeatNumber();

    LocalDate getStartDate();

    LocalTime getStartTime();

    String getMovieTitle();
}
