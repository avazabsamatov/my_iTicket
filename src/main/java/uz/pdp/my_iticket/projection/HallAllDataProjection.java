package uz.pdp.my_iticket.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface HallAllDataProjection {
    UUID getId();

    String getName();

    Double getVipAdditionalFeeInPercent();

    @Value( "#{@rowRepository.findRowsByHallId({target.id})}")
    List<RowAllDataProjection> getRows();
}
