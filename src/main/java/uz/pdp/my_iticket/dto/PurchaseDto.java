package uz.pdp.my_iticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseDto {

    private List<UUID> tickIds;

    private UUID payTypeId;
}
