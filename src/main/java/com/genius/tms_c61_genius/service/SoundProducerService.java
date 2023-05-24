package com.genius.tms_c61_genius.service;

import com.genius.tms_c61_genius.model.request.SoundProducerReqDto;
import com.genius.tms_c61_genius.model.response.SoundProducerResDto;

public interface SoundProducerService {
    SoundProducerResDto createSoundProducer(SoundProducerReqDto soundProducerReqDto);

    void deleteProducerById(Integer id);
    void deleteProducerByNickName(String nickName);
    SoundProducerResDto updateProducer(SoundProducerReqDto soundProducerReqDto);

    SoundProducerResDto getProducerById(Integer id);
    SoundProducerResDto getProducerByNickname(String nickName);
}
