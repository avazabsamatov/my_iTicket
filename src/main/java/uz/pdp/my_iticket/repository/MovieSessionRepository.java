package uz.pdp.my_iticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.my_iticket.model.MovieSession;
import uz.pdp.my_iticket.projection.AvailableSeatProjection;
import uz.pdp.my_iticket.projection.HallSessionProjection;
import uz.pdp.my_iticket.projection.MovieAllSessionProjection;
import uz.pdp.my_iticket.projection.TimeSessionProjection;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface MovieSessionRepository extends JpaRepository<MovieSession, UUID> {

    @Query(nativeQuery = true,value = "SELECT\n" +
            "       cast(m.id as varchar) as movieId,\n" +
            "       cast(a.id as varchar) as moviePosterImageId,\n" +
            "       cast(ma.id as varchar) as movieAnnouncementId,\n" +
            "       m.title as movieTitle,\n" +
            "       ms.start_date as startDate\n" +
            "from movie_sessions ms\n" +
            "         join movie_announcements ma on ma.id = ms.movie_announcement_id\n" +
            "         join halls h on h.id = ms.hall_id\n" +
            "         join movies m on m.id = ma.movie_id\n" +
            "         join attachments a on a.id = m.poster_img_id\n" +
            "where start_date >= cast(now() as date) and ma.is_active = true\n" +
            "group by ms.start_date, m.id,a.id,ma.id\n" +
            "order by ms.start_date;")
    List<MovieAllSessionProjection> findAllByProjection();

    @Query(value = "select cast(m.id as varchar) as id from movie_sessions m where m.movie_announcement_id = :id",nativeQuery = true)
    List<UUID> findMovieSessionByMovieAnnouncementId(UUID id);

    @Query(nativeQuery = true,value = "select exists(select ms.id from movie_sessions ms\n" +
            "    where ms.hall_id= :hallId and ms.start_date= :date\n" +
            "              and :endTime >= ms.start_time and :startTime <= ms.end_time );")
    boolean isTimeRangeExist(UUID hallId, LocalDate date, LocalTime startTime,LocalTime endTime);


    @Query(nativeQuery = true,value = "SELECT\n" +
            "    cast(h.id as varchar) as id,\n" +
            "    cast(ma.id as varchar) as movieAnnouncementId,\n" +
            "    h.name as hallName,\n" +
            "       ms.start_date as startDate\n" +
            "from movie_sessions ms\n" +
            "         join movie_announcements ma on ma.id = ms.movie_announcement_id\n" +
            "         join halls h on h.id = ms.hall_id\n" +
            "         join movies m on m.id = ma.movie_id\n" +
            "         join attachments a on a.id = m.poster_img_id\n" +
            "where ma.id= :movieAnnouncementId and ma.is_active=true and ms.start_date= :date\n" +
            "group by h.id,ma.id,ms.start_date")
    HallSessionProjection getSessionHall(UUID movieAnnouncementId,LocalDate date);


    @Query(nativeQuery = true,value = "SELECT\n" +
            "    cast(ms.id as varchar) as sessionId,\n" +
            "    ms.start_time as startTime\n" +
            "from movie_sessions ms\n" +
            "         join movie_announcements ma on ma.id = ms.movie_announcement_id\n" +
            "         join halls h on h.id = ms.hall_id\n" +
            "         join movies m on m.id = ma.movie_id\n" +
            "         join attachments a on a.id = m.poster_img_id\n" +
            "where ma.id= :movieAnnouncementId\n" +
            "  and ms.start_date= :date\n" +
            "  and h.id= :hallId and ma.is_active=true\n" +
            "group by ms.id,ms.start_time\n" +
            "order by ms.start_time")
    List<TimeSessionProjection> getHallTime(UUID hallId, UUID movieAnnouncementId, LocalDate date);


    @Query(nativeQuery = true,value = "select cast(s.id as varchar) as id,\n" +
            "       s.number              as seatNumber,\n" +
            "       r.number              as rowNumber,\n" +
            "       true                  as available\n" +
            "from seats s\n" +
            "         join rowss r on r.id = s.row_id\n" +
            "         join halls h on h.id = r.hall_id\n" +
            "         join movie_sessions ms on h.id = ms.hall_id\n" +
            "where s.id not in (\n" +
            "    select t.seat_id\n" +
            "    from tickets t\n" +
            "    where t.movie_session_id = :sessionId\n" +
            "      and t.ticket_status <> 'REFUNDED'\n" +
            ")\n" +
            "  and ms.id = :sessionId\n" +
            "union\n" +
            "select cast(s.id as varchar) as id,\n" +
            "       s.number              as seatNumber,\n" +
            "       r.number              as rowNumber,\n" +
            "       false                 as available\n" +
            "from seats s\n" +
            "         join rowss r on r.id = s.row_id\n" +
            "         join halls h on h.id = r.hall_id\n" +
            "         join movie_sessions ms on h.id = ms.hall_id\n" +
            "         join tickets t2 on s.id = t2.seat_id\n" +
            "where t2.movie_session_id = :sessionId\n" +
            "  and t2.ticket_status <> 'REFUNDED'\n" +
            "order by rowNumber, seatNumber;")
    List<AvailableSeatProjection> getAvailableSeat(UUID sessionId);
}
