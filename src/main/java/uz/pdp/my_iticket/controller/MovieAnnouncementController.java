package uz.pdp.my_iticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.my_iticket.dto.MovieAnnouncementDto;
import uz.pdp.my_iticket.service.MovieAnnouncementService;

import java.util.UUID;

@RestController
@RequestMapping("/api/announcement")
public class MovieAnnouncementController {

    @Autowired
    MovieAnnouncementService movieAnnouncementService;

    @GetMapping
    public ResponseEntity<?> getAllMovieAnnouncement(){
        return movieAnnouncementService.getAllMovieAnnouncement();
    }

    @PostMapping
    public ResponseEntity<?> addMovieAnnouncement(@RequestBody MovieAnnouncementDto movieAnnouncementDto){
        return movieAnnouncementService.addMovieAnnouncement(movieAnnouncementDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editMovieAnnouncement(@PathVariable UUID id, @RequestBody MovieAnnouncementDto movieAnnouncementDto){
        return movieAnnouncementService.editMovieAnnouncement(id,movieAnnouncementDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieAnnouncement(@PathVariable UUID id){
        return movieAnnouncementService.deleteMovieAnnouncement(id);
    }
}
