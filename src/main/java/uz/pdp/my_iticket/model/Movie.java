package uz.pdp.my_iticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.my_iticket.enums.MovieStatus;
import uz.pdp.my_iticket.template.AbsEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "movies")
public class Movie extends AbsEntity {

    @NotEmpty
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private Integer durationInMin;

    private double  ticketInitPrice;

    @Column(nullable = false)
    private String trailerVideoUrl;

    @Column(columnDefinition = "date")
    private LocalDate releaseDate;

    private Double budget;

    @Enumerated(value = EnumType.STRING)
    private MovieStatus movieStatus;

    @Column(nullable = false)
    private Double distributorShareInPercentage;

    @OneToOne
    @JoinColumn(nullable = false)
    private Attachment posterImg;

    @ManyToMany
    private List<Cast> casts;

    @ManyToMany
    private List<Genre> genres;

    @ManyToMany
    private List<Country> countries;

    @ManyToMany
    private List<Distributor> distributors;




}
