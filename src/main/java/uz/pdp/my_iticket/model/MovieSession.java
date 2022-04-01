package uz.pdp.my_iticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.my_iticket.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "movie_sessions")
public class MovieSession extends AbsEntity {

    @ManyToOne
    private Hall hall;


    private LocalDate startDate;


    private LocalTime startTime;


    private LocalTime endTime;

    private double sessionAdditionalFeeInPercent=0;

    @ManyToOne
    private MovieAnnouncement movieAnnouncement;

}
