package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.my_iticket.model.Distributor;
import uz.pdp.my_iticket.projection.DistributorProjection;

import java.util.List;
import java.util.UUID;

public interface DistributorRepository extends JpaRepository<Distributor, UUID> {
    Distributor findDistributorById(UUID id);

    @Query(nativeQuery = true,value = "SELECT cast(d.id as varchar) as id, d.name as name from distributors d join movies_distributors md on d.id = md.distributors_id where md.movies_id = :id")
    List<DistributorProjection> findDistributorsByMovieId(UUID id);

    @Query(nativeQuery = true,value =
    "select cast(m.id as varchar) as id,\n" +
            "       d.name,\n" +
            "       d.description\n" +
            "from movies_distributors md\n" +
            "         join distributors d on md.distributors_id = d.id\n" +
            "         join movies m on md.movies_id = m.id\n" +
            "where m.id=:movieId")
    List<DistributorProjection> getDistributorByMovieId(UUID movieId);
}
