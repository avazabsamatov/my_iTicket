package uz.pdp.my_iticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.my_iticket.template.AbsEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "distributors")
public class Distributor extends AbsEntity {

    @NotEmpty
    private String name;

    @Column(columnDefinition = "text")
    private String description;

//    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
//            (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Attachment logo;

}
