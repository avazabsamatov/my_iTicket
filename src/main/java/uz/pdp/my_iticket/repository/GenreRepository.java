package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.my_iticket.model.Genre;
import uz.pdp.my_iticket.projection.GenreProjection;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(path = "genre",collectionResourceRel = "genre")
public interface GenreRepository extends JpaRepository<Genre, UUID> {

    @Query(nativeQuery = true,value = "SELECT cast(g.id as varchar) as id , g.name as name from genres g join movies_genres mg on g.id = mg.genres_id where mg.movies_id= :id")
    List<GenreProjection> findGenresByMovieId(UUID id);

    @Query(nativeQuery = true,value =
    "select cast(g.id as varchar) as id,\n" +
            "       g.name\n" +
            "from movies_genres mg join genres g on g.id = mg.genres_id\n" +
            "                      join movies m on mg.movies_id = m.id\n" +
            "where m.id=:movieId")
    List<GenreProjection> getGenreByMovieId(UUID movieId);
}
