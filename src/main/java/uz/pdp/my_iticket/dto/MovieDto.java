package uz.pdp.my_iticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {

    private String title;
    private String description;
    private Integer durationInMin;
    private double  ticketInitPrice;
    private String trailerVideoUrl;
    private LocalDate releaseDate;
    private Double budget;
    private String movieStatus;
    private Double distributorShareInPercentage;
    private List<UUID> castIds;
    private List<UUID> genreIds;
    private List<UUID> countryIds;
    private List<UUID> distributorIds;

}
