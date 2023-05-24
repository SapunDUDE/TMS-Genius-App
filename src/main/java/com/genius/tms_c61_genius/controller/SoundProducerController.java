package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.aspect.TrackExecutionTime;
import com.genius.tms_c61_genius.model.request.SoundProducerReqDto;
import com.genius.tms_c61_genius.model.response.SoundProducerResDto;
import com.genius.tms_c61_genius.service.SoundProducerService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class SoundProducerController {
    private final SoundProducerService soundProducerService;

    @Autowired
    public SoundProducerController(SoundProducerService soundProducerService) {
        this.soundProducerService = soundProducerService;
    }

    @PostMapping()
    @TrackExecutionTime
    ResponseEntity<SoundProducerResDto> createSoundProducer(@RequestBody SoundProducerReqDto soundProducerReqDto) {
        return new ResponseEntity<>(soundProducerService.createSoundProducer(soundProducerReqDto), HttpStatus.CREATED);
    }

    @PutMapping()
    @TrackExecutionTime
    ResponseEntity<SoundProducerResDto> updateSoundProducer(@RequestBody SoundProducerReqDto soundProducerReqDto) {
        return new ResponseEntity<>(soundProducerService.updateProducer(soundProducerReqDto), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<SoundProducerResDto> getSoundProducerById(@RequestParam Integer id) {
        return new ResponseEntity<>(soundProducerService.getProducerById(id), HttpStatus.OK);
    }

    @GetMapping("/{nickname}")
    ResponseEntity<SoundProducerResDto> getSoundProducerByNickName(@PathVariable String nickname) {
        return new ResponseEntity<>(soundProducerService.getProducerByNickname(nickname), HttpStatus.OK);
    }


    @DeleteMapping()
    @TrackExecutionTime
    ResponseEntity<HttpStatus> deleteSoundProducerById(@RequestParam Integer id) {
        soundProducerService.deleteProducerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{nickname}")
    @TrackExecutionTime
    ResponseEntity<HttpStatus> deleteSoundProducerByNickName(@PathVariable String nickname) {
        soundProducerService.deleteProducerByNickName(nickname);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
