package uz.pdp.my_iticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.my_iticket.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "pay_types")
public class PayType extends AbsEntity {

    @NotEmpty
    private String name;

    @OneToOne
    private Attachment logo;
    
}
