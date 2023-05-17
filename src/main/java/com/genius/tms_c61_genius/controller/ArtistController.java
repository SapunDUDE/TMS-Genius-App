package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.model.request.ArtistReqDto;
import com.genius.tms_c61_genius.model.request.UpdateArtistReqDto;
import com.genius.tms_c61_genius.model.response.ArtistResDto;
import com.genius.tms_c61_genius.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArtistController {
    private final ArtistService artistService;
    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping("/admin/artist")
    public ResponseEntity<ArtistResDto> createArtist(@RequestBody ArtistReqDto artistReqDto){
        return new ResponseEntity<>(artistService.createArtist(artistReqDto), HttpStatus.CREATED);
    }

    @PutMapping("/artist")
    public ResponseEntity<ArtistResDto> updateArtist(@RequestBody UpdateArtistReqDto updateArtistReqDto){
        return new ResponseEntity<>(artistService.updateArtist(updateArtistReqDto),HttpStatus.OK);
    }
    @DeleteMapping("/artist")
    public ResponseEntity<HttpStatus> deleteArtistByUserLogin(@RequestParam String userlogin){
        artistService.deleteArtistByUser(userlogin);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/artist/{nickname}")
    public ResponseEntity<HttpStatus> deleteArtistByNickName(@PathVariable String nickname){
        artistService.deleteArtistByNickName(nickname);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/artist/{nickname}")
    public ResponseEntity<ArtistResDto>getArtistByNickName(@PathVariable String nickname){
        return new ResponseEntity<>(artistService.getArtistByNickName(nickname),HttpStatus.OK);
    }

    @GetMapping("/artist")
    public ResponseEntity<ArtistResDto>getArtistByUser(@RequestParam String userlogin){
        return new ResponseEntity<>(artistService.getArtistByUser(userlogin),HttpStatus.OK);
    }
}
