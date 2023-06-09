package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.aspect.TrackExecutionTime;
import com.genius.tms_c61_genius.model.request.LabelReqDto;
import com.genius.tms_c61_genius.model.response.LabelResDto;
import com.genius.tms_c61_genius.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/label")
public class LabelController {
    private final LabelService labelService;

    @Autowired
    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @PostMapping()
    @TrackExecutionTime
    ResponseEntity<LabelResDto> createLabel(@RequestBody LabelReqDto labelReqDto) {
        System.out.println(labelReqDto);
        return new ResponseEntity<>(labelService.createLabel(labelReqDto), HttpStatus.CREATED);
    }

    @PutMapping()
    @TrackExecutionTime
    ResponseEntity<LabelResDto> updateLabelInfo(@RequestBody LabelReqDto labelReqDto) {
        return new ResponseEntity<>(labelService.updateLabelInfo(labelReqDto), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    @TrackExecutionTime
    ResponseEntity<HttpStatus> deleteLabel(@PathVariable String name) {
        labelService.deleteLabel(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{name}")
    ResponseEntity<LabelResDto> getLabel(@PathVariable String name) {
        return new ResponseEntity<>(labelService.getLabelInfo(name), HttpStatus.OK);
    }
}
