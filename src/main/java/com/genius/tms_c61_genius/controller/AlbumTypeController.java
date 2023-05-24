package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.aspect.TrackExecutionTime;
import com.genius.tms_c61_genius.service.AlbumTypeService;
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
@RequestMapping("/albumtype")
public class AlbumTypeController {
    private final AlbumTypeService albumTypeService;

    @Autowired
    public AlbumTypeController(AlbumTypeService albumTypeService) {
        this.albumTypeService = albumTypeService;
    }

    @GetMapping()
    ResponseEntity<String> getAlbumType(@RequestParam Integer typeid) {
        return new ResponseEntity<>(albumTypeService.getAlbumType(typeid), HttpStatus.OK);
    }

    @PostMapping()
    @TrackExecutionTime
    ResponseEntity<String> createAlbumType(@RequestParam String typename) {
        return new ResponseEntity<>(albumTypeService.createAlbumType(typename), HttpStatus.CREATED);
    }

    @PutMapping()
    @TrackExecutionTime
    ResponseEntity<String> updateAlbumType(@RequestParam Integer typeid, @RequestParam String typename) {
        return new ResponseEntity<>(albumTypeService.updateAlbumType(typeid, typename), HttpStatus.OK);
    }

    @DeleteMapping()
    @TrackExecutionTime
    ResponseEntity<HttpStatus> deleteAlbumType(@RequestParam Integer typeid) {
        albumTypeService.deleteAlbumType(typeid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
