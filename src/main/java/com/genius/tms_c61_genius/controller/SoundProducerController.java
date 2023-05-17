package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.model.request.SoundProducerReqDto;
import com.genius.tms_c61_genius.model.response.SoundProducerResDto;
import com.genius.tms_c61_genius.service.SoundProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SoundProducerController {
    private final SoundProducerService soundProducerService;
    @Autowired
    public SoundProducerController(SoundProducerService soundProducerService) {
        this.soundProducerService = soundProducerService;
    }

    @PostMapping("/admin/producer")
    ResponseEntity<SoundProducerResDto>createSoundProducer(@RequestBody SoundProducerReqDto soundProducerReqDto){
        return new ResponseEntity<>(soundProducerService.createSoundProducer(soundProducerReqDto), HttpStatus.CREATED);
    }

    @PutMapping("/admin/producer")
    ResponseEntity<SoundProducerResDto>updateSoundProducer(@RequestBody SoundProducerReqDto soundProducerReqDto){
        return new ResponseEntity<>(soundProducerService.updateProducer(soundProducerReqDto),HttpStatus.OK);
    }

    @GetMapping("/producer")
    ResponseEntity<SoundProducerResDto>getSoundProducerById(@RequestParam Integer id){
        return new ResponseEntity<>(soundProducerService.getProducerById(id),HttpStatus.OK);
    }

    @GetMapping("/producer/{nickname}")
    ResponseEntity<SoundProducerResDto>getSoundProducerByNickName(@PathVariable String nickname){
        return new ResponseEntity<>(soundProducerService.getProducerByNickname(nickname),HttpStatus.OK);
    }


    @DeleteMapping("/admin/producer")
    ResponseEntity<HttpStatus>deleteSoundProducerById(@RequestParam Integer id){
        soundProducerService.deleteProducerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/admin/producer/{nickname}")
    ResponseEntity<HttpStatus>deleteSoundProducerByNickName(@PathVariable String nickname){
        soundProducerService.deleteProducerByNickName(nickname);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
