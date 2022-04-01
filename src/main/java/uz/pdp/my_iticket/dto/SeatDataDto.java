package uz.pdp.my_iticket.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatDataDto {
    private Integer number;
    private UUID priceCategoryId;
}
