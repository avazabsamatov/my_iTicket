package uz.pdp.my_iticket.projection;

import java.util.UUID;

public interface SeatAllDataProjection {
    UUID getId();

    Integer getNumber();

    UUID getPriceCategoryId();
}
