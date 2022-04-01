package uz.pdp.my_iticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RowDto {
    private Integer number;
    private List<SeatDto> seats;
}
