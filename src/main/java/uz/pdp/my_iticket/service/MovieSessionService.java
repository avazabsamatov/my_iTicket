package uz.pdp.my_iticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.my_iticket.dto.MovieSessionDto;
import uz.pdp.my_iticket.dto.ReservedHallDto;
import uz.pdp.my_iticket.model.*;
import uz.pdp.my_iticket.payload.ApiResponse;
import uz.pdp.my_iticket.projection.AvailableSeatProjection;
import uz.pdp.my_iticket.repository.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static uz.pdp.my_iticket.utils.Constants.*;

@Service
public class MovieSessionService {

    @Autowired
    MovieSessionRepository movieSessionRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieAnnouncementRepository movieAnnouncementRepository;

    @Autowired
    ExtraTimeBetweenSessionsRepository extraTimeBetweenSessionsRepository;

    @Autowired
    SessionAdditionalFeeInPercentRepository inPercentRepository;

    @Autowired
    HallRepository hallRepository;

    public ResponseEntity<?> getAllMovieSession() {
        return ResponseEntity.ok(new ApiResponse(SUCCESS, true, movieSessionRepository.findAllByProjection()));
    }

    @Transactional
    public ResponseEntity<?> addMovieSession(MovieSessionDto movieSessionDto) {
        Integer extraTime = extraTimeBetweenSessionsRepository.findFirst();
        MovieAnnouncement movieAnnouncement;
        Hall hall;
        List<MovieSession> movieSessions = new ArrayList<>();
        List<MovieSession> movieSessionList=new ArrayList<>();
        try {
            try {
                movieAnnouncement = movieAnnouncementRepository.findById(movieSessionDto.getMovieAnnouncementId()).orElseThrow(() -> new ResourceNotFoundException("Movie announcement not found"));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(OBJECT_NOT_FOUND, false, null));
            }
            for (ReservedHallDto reservedHallDto : movieSessionDto.getReservedHallDtoList()) {
                try {
                    hall = hallRepository.findById(reservedHallDto.getHallId()).orElseThrow(() -> new ResourceNotFoundException("Hall not found!"));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(OBJECT_NOT_FOUND, false, null));
                }

                if (reservedHallDto.getEndDate() != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate startDate = LocalDate.parse(reservedHallDto.getStartDate(), formatter);
                    LocalDate endDate = LocalDate.parse(reservedHallDto.getEndDate(), formatter);
                    long end = endDate.toEpochDay() - startDate.toEpochDay();
                    if (end > 0) {
                        for (int i = 0; i <= end; i++) {
                            LocalDate date = startDate.plusDays(i);
                            for (String startTime : reservedHallDto.getStartTimes()) {
                                LocalTime time = LocalTime.parse(startTime);
                                int minute = time.getMinute() + time.getHour() * 60;
                                Integer endTime = movieAnnouncement.getMovie().getDurationInMin() + minute + extraTime;
                                LocalTime endTimeSession = LocalTime.of(endTime / 60, endTime % 60);
                                boolean timeRangeExist = movieSessionRepository.isTimeRangeExist(reservedHallDto.getHallId(), date, time, endTimeSession);
                                if (timeRangeExist) {
                                    String res = "The hall is busy at " + time + " on " + date;
                                    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(res, false, null));
                                }
                                double sessionAdditionalFeeInPercent = inPercentRepository.checkTime(time);
                                MovieSession movieSession = new MovieSession(hall, date, time, endTimeSession, sessionAdditionalFeeInPercent, movieAnnouncement);
                                movieSessions.add(movieSession);
                            }
                        }
                    }
                     movieSessionList = movieSessionRepository.saveAll(movieSessions);

                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate startDate = LocalDate.parse(reservedHallDto.getStartDate(), formatter);
                    for (String startTime : reservedHallDto.getStartTimes()) {
                        LocalTime time = LocalTime.parse(startTime);
                        int minute = time.getMinute() + time.getHour() * 60;
                        Integer endTime = movieAnnouncement.getMovie().getDurationInMin() + minute + extraTime;
                        LocalTime endTimeSession = LocalTime.of(endTime / 60, endTime % 60);
                        boolean timeRangeExist = movieSessionRepository.isTimeRangeExist(reservedHallDto.getHallId(), startDate, time, endTimeSession);
                        if (timeRangeExist) {
                            String res = "The hall is busy at " + time + " on " + startDate;
                            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(res, false, null));
                        }
                        double sessionAdditionalFeeInPercent = inPercentRepository.checkTime(time);
                        MovieSession movieSession = new MovieSession(hall, startDate, time, endTimeSession, sessionAdditionalFeeInPercent, movieAnnouncement);
                        movieSessions.add(movieSession);
                    }
                    movieSessionList = movieSessionRepository.saveAll(movieSessions);
                }

            }
            return ResponseEntity.ok(new ApiResponse(SUCCESS_SAVE,true,movieSessionList));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(FAILED_TO_SAVE, false, null));
        }
    }

    public ResponseEntity<?> deleteMovieSession(UUID id) {
        if (id == null || id.equals(""))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(ERROR, false, null));
        try {
            movieSessionRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse(SUCCESS_DELETE, true, null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(FAILED_TO_DELETE, false, null));
        }
    }

    public ResponseEntity<?> getSessionAvailableSeat(UUID id) {
        if (id == null || id.equals(""))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(ERROR, false, null));
    movieSessionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie session not found!"));
    try {
        List<AvailableSeatProjection> availableSeats = movieSessionRepository.getAvailableSeat(id);
        return ResponseEntity.ok(new ApiResponse(SUCCESS,true,availableSeats));
    }catch (Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(OBJECT_NOT_FOUND,false,null));
    }
    }
}
