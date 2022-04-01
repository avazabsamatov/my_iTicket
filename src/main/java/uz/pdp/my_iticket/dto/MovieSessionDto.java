package uz.pdp.my_iticket.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieSessionDto {
    private UUID movieAnnouncementId;
    private List<ReservedHallDto> reservedHallDtoList;
}
