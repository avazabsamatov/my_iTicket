package uz.pdp.my_iticket.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatDto {
    private Integer startSeatNumber;
    private Integer endSeatNumber;
    private UUID priceCategoryId;
}
