package uz.pdp.my_iticket.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservedHallDto {
    private UUID hallId;
    private String startDate;
    private String endDate;
    private List<String> startTimes;
}
