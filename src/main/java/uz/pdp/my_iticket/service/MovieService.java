package uz.pdp.my_iticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.my_iticket.model.Movie;
import uz.pdp.my_iticket.payload.ApiResponse;
import uz.pdp.my_iticket.projection.MovieAllProjection;
import uz.pdp.my_iticket.projection.MovieProjection;
import uz.pdp.my_iticket.repository.MovieRepository;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;


    public ResponseEntity<?> getAllMovie() {
        List<MovieAllProjection> allMovie = movieRepository.getAllMovie();
        return ResponseEntity.ok(new ApiResponse("Success",true,allMovie));
    }

    public ResponseEntity getMovieById(UUID id) {
        MovieProjection movieById = movieRepository.getMovieById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Successfully",true,movieById));
    }
}
