package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.aspect.TrackExecutionTime;
import com.genius.tms_c61_genius.model.request.AlbumReqDto;
import com.genius.tms_c61_genius.model.response.AlbumResDto;
import com.genius.tms_c61_genius.model.response.SongResDto;
import com.genius.tms_c61_genius.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/create")
    @TrackExecutionTime
    ResponseEntity<AlbumResDto> createAlbum(@RequestBody AlbumReqDto albumReqDto) {
        return new ResponseEntity<>(albumService.createAlbum(albumReqDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    @TrackExecutionTime
    ResponseEntity<HttpStatus> deleteAlbum(@RequestParam String title) {
        albumService.deleteAlbum(title);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{title}")
    ResponseEntity<AlbumResDto> getAlbum(@PathVariable String title) {
        return new ResponseEntity<>(albumService.getAlbumByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/producers/{title}")
    ResponseEntity<List<String>> getProducers(@PathVariable String title) {
        return new ResponseEntity<>(albumService.getProducers(title), HttpStatus.OK);
    }

    @GetMapping("/artists/{title}")
    ResponseEntity<List<String>> getArtists(@PathVariable String title) {
        return new ResponseEntity<>(albumService.getArtists(title), HttpStatus.OK);
    }

    @GetMapping("/songs/{title}")
    ResponseEntity<List<SongResDto>> getSongs(@PathVariable String title) {
        return new ResponseEntity<>(albumService.getSongs(title), HttpStatus.OK);
    }
}
