package uz.pdp.my_iticket.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MovieProjection {
    UUID getId();

    String getTitle();

    String getDescription();

    Integer getDurationMin();

    Double getTicketInitPrice();

    String getTrailerVideoUrl();

    LocalDate getReleaseDate();

    Double getBudget();

    Double getDistributorShareInPercentage();


    @Value("#{@attachmentRepository.getAttachmentByMovieId(target.id)}")
    List<AttachmentProjection> getPosterImage();

    @Value("#{@castRepository.getCastByMovieId(target.id)}")
    List<CastProjection> getCast();

    @Value("#{@genreRepository.getGenreByMovieId(target.id)}")
    List<GenreProjection> getGenre();

    @Value("#{@distributorRepository.getDistributorByMovieId(target.id)}")
    List<DistributorProjection> getDistributor();
}
