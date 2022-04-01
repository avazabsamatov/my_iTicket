package uz.pdp.my_iticket.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.my_iticket.model.Movie;
import uz.pdp.my_iticket.projection.MovieAllProjection;
import uz.pdp.my_iticket.projection.MovieProjection;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    @Query(nativeQuery = true,value =
    "select cast(m.id as varchar) as id,\n" +
            "       m.title,\n" +
            "       m.description,\n" +
            "       m.duration_in_min as durationMin,\n" +
            "       m.ticket_init_price as ticketInitPrice,\n" +
            "       m.trailer_video_url as trailerVideoUrl,\n" +
            "       m.release_date as releaseDate,\n" +
            "       m.budget,\n" +
//            "       m.movie_status as movieStatus,\n" +
            "       m.distributor_share_in_percentage as distributorShareInPercentage\n" +
            "from movies m where m.id=:movieId")
    MovieProjection getMovieById(UUID movieId);

    @Query(nativeQuery = true,value =
    "select cast(m.id as varchar) as id,\n" +
            "       m.title as title,\n" +
            "       m.description as description\n" +
            "from movies m;")
    List<MovieAllProjection> getAllMovie();
}
