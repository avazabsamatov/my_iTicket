package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.my_iticket.model.Row;
import uz.pdp.my_iticket.projection.RowAllDataProjection;
import uz.pdp.my_iticket.projection.RowProjection;

import java.util.List;
import java.util.UUID;

public interface RowRepository extends JpaRepository<Row, UUID> {
    List<Row> findRowByHallId(UUID id);


    @Query(nativeQuery = true,value = "select cast(r.id as varchar) as id," +
            "r.number as number from rowss r where r.hall_id = :id")
    List<RowAllDataProjection> findRowsByHallId(UUID id);

    @Query(value = "select cast(r.id as varchar) as id, number from rowss r where r.hall_id = :hallId ",nativeQuery = true)
    List<RowProjection> findRowsAllByHallId(UUID hallId);

    @Query(value = "select cast(r.id as varchar) as id, number from rowss r where r.id = :rowId",nativeQuery = true)
    RowAllDataProjection findRowsById(UUID rowId);
}
