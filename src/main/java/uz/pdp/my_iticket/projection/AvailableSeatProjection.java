package uz.pdp.my_iticket.projection;

import java.util.UUID;

public interface AvailableSeatProjection {
    UUID getId();

    String getSeatNumber();

    String getRowNumber();

    Boolean getAvailable();
}
