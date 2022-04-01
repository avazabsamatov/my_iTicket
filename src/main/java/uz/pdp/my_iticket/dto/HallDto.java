package uz.pdp.my_iticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HallDto {
    private String name;
    private Double vipAdditionalFeeInPercent;
    private List<uz.pdp.my_iticket.dto.RowDto> rows;
}
