package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.my_iticket.model.Row;
import uz.pdp.my_iticket.model.Seat;
import uz.pdp.my_iticket.projection.SeatAllDataProjection;

import java.util.List;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, UUID> {
    @Query(value = "select cast(s.id as varchar) as id from seats s where s.row_id = :id", nativeQuery = true)
    List<UUID> findSeatByRowId(UUID id);


    Seat findFirstByRowOrderByCreatedAtDesc(Row row);

    @Query(value = "select cast(s.id as varchar) as id," +
            "s.number as number," +
            "cast(s.price_category_id as varchar) as priceCategoryId from seats s where s.id = :id",nativeQuery = true)
    SeatAllDataProjection findSeatsById(UUID id);

    boolean existsSeatByNumberAndRow(Integer number, Row row);

    @Query(nativeQuery = true,value = "SELECT cast(id as varchar) as id from seats where  row_id=:rowId  order by created_at desc limit :num")
    List<UUID> findSeatsByRowIdAndAndOrderByCreatedByIdDesc(UUID rowId,Integer num);

    @Query(value = "select cast(s.id as varchar) as id," +
            "s.number as number," +
            "cast(s.price_category_id as varchar) as priceCategoryId from seats s where s.row_id = :id",nativeQuery = true)
    List<SeatAllDataProjection> findSeatsByRowId(UUID id);
}
