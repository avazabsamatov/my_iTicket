package uz.pdp.my_iticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.my_iticket.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "seats")
public class Seat extends AbsEntity {

    private Integer number;
    @ManyToOne
    private PriceCategory priceCategory;
    @ManyToOne
    private Row row;

}
