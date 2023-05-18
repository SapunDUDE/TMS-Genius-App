package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.model.request.AlbumReqDto;
import com.genius.tms_c61_genius.model.response.AlbumResDto;
import com.genius.tms_c61_genius.model.response.SongResDto;
import com.genius.tms_c61_genius.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
public class AlbumController {
    private final AlbumService albumService;
    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/album/create")
    ResponseEntity<AlbumResDto>createAlbum(@RequestBody AlbumReqDto albumReqDto){
        return new ResponseEntity<>(albumService.createAlbum(albumReqDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/album/delete")
    ResponseEntity<HttpStatus>deleteAlbum(@RequestParam String title){
        albumService.deleteAlbum(title);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/album/{title}")
    ResponseEntity<AlbumResDto>getAlbum(@PathVariable String title){
        return new ResponseEntity<>(albumService.getAlbumByTitle(title),HttpStatus.OK);
    }

    @GetMapping("/album/producers/{title}")
    ResponseEntity<List<String>>getProducers(@PathVariable String title){
        return new ResponseEntity<>(albumService.getProducers(title),HttpStatus.OK);
    }

    @GetMapping("/album/artists/{title}")
    ResponseEntity<List<String>>getArtists(@PathVariable String title){
        return new ResponseEntity<>(albumService.getArtists(title),HttpStatus.OK);
    }
    @GetMapping("/album/songs/{title}")
    ResponseEntity<List<SongResDto>>getSongs(@PathVariable String title){
        return new ResponseEntity<>(albumService.getSongs(title),HttpStatus.OK);
    }
}
