package uz.pdp.my_iticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.my_iticket.service.MovieService;

import java.util.UUID;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    MovieService movieService;
    @GetMapping
    public ResponseEntity<?> getAllMovie(){
        return movieService.getAllMovie();
    }

    @GetMapping("/{id}")
    public ResponseEntity getMovieById(@PathVariable UUID id){
        return movieService.getMovieById(id);
    }
}
