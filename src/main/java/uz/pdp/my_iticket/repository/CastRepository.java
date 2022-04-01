package uz.pdp.my_iticket.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.my_iticket.model.Cast;
import uz.pdp.my_iticket.projection.CastProjection;

import java.util.List;
import java.util.UUID;

//@RepositoryRestResource(path = "/api/cast",collectionResourceRel = "cast")
public interface CastRepository extends JpaRepository<Cast, UUID> {

    @Query(nativeQuery = true, value = "SELECT cast(c.id as varchar) as id, c.full_name as fullName, c.cast_type as castType from casts c join movies_casts mc on c.id = mc.casts_id where mc.movies_id= :id")
    List<CastProjection> findCastsByMovieId(UUID id);

    @Query(nativeQuery = true, value =
            "select cast(c.id as varchar ) as id," +
                    "c.full_name as fullName," +
                    "c.cast_type as castType from casts c")
    Page<CastProjection> getAllCast(Pageable pageable);

    @Query(nativeQuery = true, value =
            "select cast(g.id as varchar) as id,\n" +
                    "       g.name\n" +
                    "from movies_genres mg join genres g on g.id = mg.genres_id\n" +
                    "                      join movies m on mg.movies_id = m.id\n" +
                    "where m.id=:movieId")
    List<CastProjection> getCastByMovieId(UUID movieId);
}
