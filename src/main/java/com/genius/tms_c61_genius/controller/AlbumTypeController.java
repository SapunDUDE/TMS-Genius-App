package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.service.AlbumTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlbumTypeController {
    private final AlbumTypeService albumTypeService;
    @Autowired
    public AlbumTypeController(AlbumTypeService albumTypeService) {
        this.albumTypeService = albumTypeService;
    }

    @GetMapping("/admin/albumtype")
    ResponseEntity<String> getAlbumType(@RequestParam Integer typeid){
        return new ResponseEntity<>(albumTypeService.getAlbumType(typeid), HttpStatus.OK);
    }

    @PostMapping("/admin/albumtype")
    ResponseEntity<String>createAlbumType(@RequestParam String typename){
        return new ResponseEntity<>(albumTypeService.createAlbumType(typename),HttpStatus.CREATED);
    }

    @PutMapping("/admin/albumtype")
    ResponseEntity<String>updateAlbumType(@RequestParam Integer typeid, @RequestParam String typename){
        return new ResponseEntity<>(albumTypeService.updateAlbumType(typeid,typename),HttpStatus.OK);
    }

    @DeleteMapping("/admin/albumtype")
    ResponseEntity<HttpStatus>deleteAlbumType(@RequestParam Integer typeid){
        albumTypeService.deleteAlbumType(typeid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
