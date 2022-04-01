package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.my_iticket.model.Ticket;
import uz.pdp.my_iticket.projection.TicketProjection;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    @Query(nativeQuery = true,value = "select m.ticket_init_price +\n" +
            "       (m.ticket_init_price * h.vip_additional_fee_in_percent / 100) +\n" +
            "       (m.ticket_init_price * ms.session_additional_fee_in_percent / 100) +\n" +
            "       (m.ticket_init_price * pc.additional_fee_in_percentage / 100) as price\n" +
            "from movie_sessions ms\n" +
            "         join movie_announcements ma on ma.id = ms.movie_announcement_id\n" +
            "         join movies m on m.id = ma.movie_id\n" +
            "         join halls h on h.id = ms.hall_id\n" +
            "         join rowss r on h.id = r.hall_id\n" +
            "         join seats s on r.id = s.row_id\n" +
            "         join price_categories pc on pc.id = s.price_category_id\n" +
            "where ms.id = :sessionId\n" +
            "  and s.id = :seatId")
    Double calculateTicketPrice(UUID seatId,UUID sessionId);

    @Query(nativeQuery = true,value = "SELECT exists(select * from tickets  where movie_session_id=:sessionId and seat_id=:seatId and ticket_status <> 'REFUNDED')")
    Boolean existsTicket(UUID seatId, UUID sessionId);

    @Query(nativeQuery = true,value = "select\n" +
            " cast(t.id as varchar) as id,\n" +
            "       t.price as price,\n" +
            "       h.name as hallName,\n" +
            "       r.number as rowNumber,\n" +
            "       s.number as seatNumber,\n" +
            "       ms.start_date as startDate,\n" +
            "       ms.start_time as startTime,\n" +
            "       m.title as movieTitle\n" +
            " from tickets t\n" +
            "join movie_sessions ms on ms.id = t.movie_session_id\n" +
            "join movie_announcements ma on ma.id = ms.movie_announcement_id\n" +
            "join halls h on h.id = ms.hall_id\n" +
            "join movies m on m.id = ma.movie_id\n" +
            "join rowss r on h.id = r.hall_id\n" +
            "join seats s on r.id = s.row_id\n" +
            "where t.seat_id = s.id")
    List<TicketProjection> findTicketsAll();

    @Query(nativeQuery = true,value = "select\n" +
            " cast(t.id as varchar) as id,\n" +
            "       t.price as price,\n" +
            "       h.name as hallName,\n" +
            "       r.number as rowNumber,\n" +
            "       s.number as seatNumber,\n" +
            "       ms.start_date as startDate,\n" +
            "       ms.start_time as startTime,\n" +
            "       m.title as movieTitle\n" +
            " from tickets t\n" +
            "join movie_sessions ms on ms.id = t.movie_session_id\n" +
            "join movie_announcements ma on ma.id = ms.movie_announcement_id\n" +
            "join halls h on h.id = ms.hall_id\n" +
            "join movies m on m.id = ma.movie_id\n" +
            "join rowss r on h.id = r.hall_id\n" +
            "join seats s on r.id = s.row_id\n" +
            "where t.seat_id = s.id and t.user_id= :userId and t.ticket_status ='NEW'")
    List<TicketProjection> getTicketsByUser(UUID userId);

    @Query(nativeQuery = true,value = "SELECT * from tickets where user_id = :userId and ticket_status = 'NEW'")
    List<Ticket> getTicketsByUserId(UUID userId);
}
