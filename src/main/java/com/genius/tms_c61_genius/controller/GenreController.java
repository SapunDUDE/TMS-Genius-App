package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.aspect.TrackExecutionTime;
import com.genius.tms_c61_genius.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GenreController {
    private final GenreService genreService;
    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/admin/genre")
    ResponseEntity<String> getGenre(@RequestParam Integer genreid){
        return new ResponseEntity<>(genreService.getGenre(genreid), HttpStatus.OK);
    }

    @PostMapping("/admin/genre")
    @TrackExecutionTime
    ResponseEntity<String>createGenre(@RequestParam String genrename){
        return new ResponseEntity<>(genreService.createGenre(genrename),HttpStatus.CREATED);
    }

    @PutMapping("/admin/genre")
    @TrackExecutionTime
    ResponseEntity<String>updateGenre(@RequestParam Integer genreid, @RequestParam String genrename){
        return new ResponseEntity<>(genreService.updateGenre(genreid,genrename),HttpStatus.OK);
    }

    @DeleteMapping("/admin/genre")
    @TrackExecutionTime
    ResponseEntity<HttpStatus>deleteGenre(@RequestParam Integer genreid){
        genreService.deleteGenre(genreid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
