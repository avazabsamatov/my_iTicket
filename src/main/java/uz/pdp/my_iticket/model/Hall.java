package uz.pdp.my_iticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.my_iticket.template.AbsEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "halls")
public class Hall extends AbsEntity {

    @NotEmpty
    private String name;
    private Double vipAdditionalFeeInPercent;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hall")
    private List<Row> rows;

    public Hall(String name, Double vipAdditionalFeeInPercent) {
        this.name = name;
        this.vipAdditionalFeeInPercent = vipAdditionalFeeInPercent;
    }
}
