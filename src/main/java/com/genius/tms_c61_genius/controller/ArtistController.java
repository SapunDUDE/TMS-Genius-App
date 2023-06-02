package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.aspect.TrackExecutionTime;
import com.genius.tms_c61_genius.model.request.ArtistReqDto;
import com.genius.tms_c61_genius.model.request.UpdateArtistReqDto;
import com.genius.tms_c61_genius.model.response.ArtistResDto;
import com.genius.tms_c61_genius.model.response.SongResDto;
import com.genius.tms_c61_genius.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping()
    @TrackExecutionTime
    public ResponseEntity<ArtistResDto> createArtist(@RequestBody ArtistReqDto artistReqDto) {
        return new ResponseEntity<>(artistService.createArtist(artistReqDto), HttpStatus.CREATED);
    }

    @PutMapping()
    @TrackExecutionTime
    public ResponseEntity<ArtistResDto> updateArtist(@RequestBody UpdateArtistReqDto updateArtistReqDto) {
        return new ResponseEntity<>(artistService.updateArtist(updateArtistReqDto), HttpStatus.OK);
    }

    @DeleteMapping()
    @TrackExecutionTime
    public ResponseEntity<HttpStatus> deleteArtistByUserLogin(@RequestParam String userlogin, Authentication auth) {
        if(auth.getName().equals(userlogin)
                || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
        ){
            artistService.deleteArtistByUser(userlogin);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{nickname}")
    @TrackExecutionTime
    public ResponseEntity<HttpStatus> deleteArtistByNickName(@PathVariable String nickname, Authentication auth) {
        if(auth.getName().equals(artistService.getUserLogin(nickname))
                || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
        ){
            artistService.deleteArtistByNickName(nickname);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<ArtistResDto> getArtistByNickName(@PathVariable String nickname) {
        return new ResponseEntity<>(artistService.getArtistByNickName(nickname), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ArtistResDto> getArtistByUser(@RequestParam String userlogin) {
        return new ResponseEntity<>(artistService.getArtistByUser(userlogin), HttpStatus.OK);
    }

    @GetMapping("/albums/{nickname}")
    public ResponseEntity<List<String>> getAlbums(@PathVariable String nickname) {
        return new ResponseEntity<>(artistService.getAlbums(nickname), HttpStatus.OK);
    }

    @GetMapping("/songs/{nickname}")
    public ResponseEntity<List<SongResDto>> getSongs(@PathVariable String nickname) {
        return new ResponseEntity<>(artistService.getSongs(nickname), HttpStatus.OK);
    }

}
