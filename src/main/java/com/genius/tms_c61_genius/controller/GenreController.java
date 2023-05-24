package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.aspect.TrackExecutionTime;
import com.genius.tms_c61_genius.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping()
    ResponseEntity<String> getGenre(@RequestParam Integer genreid) {
        return new ResponseEntity<>(genreService.getGenre(genreid), HttpStatus.OK);
    }

    @PostMapping()
    @TrackExecutionTime
    ResponseEntity<String> createGenre(@RequestParam String genrename) {
        return new ResponseEntity<>(genreService.createGenre(genrename), HttpStatus.CREATED);
    }

    @PutMapping()
    @TrackExecutionTime
    ResponseEntity<String> updateGenre(@RequestParam Integer genreid, @RequestParam String genrename) {
        return new ResponseEntity<>(genreService.updateGenre(genreid, genrename), HttpStatus.OK);
    }

    @DeleteMapping()
    @TrackExecutionTime
    ResponseEntity<HttpStatus> deleteGenre(@RequestParam Integer genreid) {
        genreService.deleteGenre(genreid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
