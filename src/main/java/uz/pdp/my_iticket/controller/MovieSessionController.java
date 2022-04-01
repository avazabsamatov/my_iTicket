package uz.pdp.my_iticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.my_iticket.dto.MovieSessionDto;
import uz.pdp.my_iticket.service.MovieSessionService;

import java.util.UUID;

@RestController
@RequestMapping("/api/movie-session")
public class MovieSessionController {

    @Autowired
    MovieSessionService movieSessionService;

    @GetMapping
    public ResponseEntity<?> getAllMovieSession(){
        return movieSessionService.getAllMovieSession();
    }


    @GetMapping("/available-seat/{id}")
    public ResponseEntity<?> getSessionAvailableSeat(@PathVariable UUID id){
        return movieSessionService.getSessionAvailableSeat(id);
    }
    @PostMapping
    public ResponseEntity<?> addMovieSession(@RequestBody MovieSessionDto movieSessionDto){
        return movieSessionService.addMovieSession(movieSessionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieSession(@PathVariable UUID id){
        return movieSessionService.deleteMovieSession(id);
    }
}
