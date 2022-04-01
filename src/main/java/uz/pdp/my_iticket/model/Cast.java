package uz.pdp.my_iticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.my_iticket.enums.CastType;
import uz.pdp.my_iticket.template.AbsEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "casts")
public class Cast extends AbsEntity {

    @NotEmpty
    private String fullName;

//    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    private Attachment photo;

    @Enumerated(EnumType.STRING)
    private CastType castType;

}
