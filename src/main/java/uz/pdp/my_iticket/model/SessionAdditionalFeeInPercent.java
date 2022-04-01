package uz.pdp.my_iticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.my_iticket.template.AbsEntity;

import javax.persistence.Entity;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SessionAdditionalFeeInPercent extends AbsEntity {
    private LocalTime time;
    private double sessionAdditionalFeeInPercent=0;
}
