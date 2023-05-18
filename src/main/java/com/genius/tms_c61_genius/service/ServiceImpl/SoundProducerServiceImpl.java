package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.exception.BadDataException;
import com.genius.tms_c61_genius.exception.NotFoundException;
import com.genius.tms_c61_genius.mapper.SoundProducerDtoMapper;
import com.genius.tms_c61_genius.model.domain.Artist;
import com.genius.tms_c61_genius.model.domain.SoundProducer;
import com.genius.tms_c61_genius.model.request.SoundProducerReqDto;
import com.genius.tms_c61_genius.model.response.SoundProducerResDto;
import com.genius.tms_c61_genius.repository.SoundProducerRepository;
import com.genius.tms_c61_genius.service.SoundProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SoundProducerServiceImpl implements SoundProducerService {
    private final SoundProducerRepository soundProducerRepository;
    private final SoundProducerDtoMapper soundProducerDtoMapper;
    @Autowired
    public SoundProducerServiceImpl(SoundProducerRepository soundProducerRepository, SoundProducerDtoMapper soundProducerDtoMapper) {
        this.soundProducerRepository = soundProducerRepository;
        this.soundProducerDtoMapper = soundProducerDtoMapper;
    }

    @Override
    @Transactional
    public SoundProducerResDto createSoundProducer(SoundProducerReqDto soundProducerReqDto) {
        if(soundProducerRepository.existsSoundProducerByPersonInfoNickname(soundProducerReqDto.getNickname()))
            throw new BadDataException("producer with such nickname is already exist");
        SoundProducer savedProducer =  soundProducerDtoMapper.producerReqToProducer(soundProducerReqDto);
        soundProducerRepository.save(savedProducer);
        return soundProducerDtoMapper.producerToProducerResDto(savedProducer);
    }

    @Override
    @Transactional
    public void deleteProducerById(Integer id) {
        if(!soundProducerRepository.existsSoundProducerById(id))
            throw new NotFoundException("producer not found");
        soundProducerRepository.deleteSoundProducerById(id);
    }

    @Override
    @Transactional
    public void deleteProducerByNickName(String nickName) {
        if(!soundProducerRepository.existsSoundProducerByPersonInfoNickname(nickName))
            throw new NotFoundException("producer not found");
        soundProducerRepository.deleteSoundProducerByPersonInfoNickname(nickName);
    }

    @Override
    @Transactional
    public SoundProducerResDto updateProducer(SoundProducerReqDto soundProducerReqDto) {
        if(!soundProducerRepository.existsSoundProducerByPersonInfoNickname(soundProducerReqDto.getNickname()))
            throw new NotFoundException("producer not found");
        SoundProducer producerToUpdate = soundProducerRepository.getSoundProducerByPersonInfoNickname(soundProducerReqDto.getNickname()).get();
        soundProducerDtoMapper.updateProducerInfo(producerToUpdate,soundProducerReqDto);
        soundProducerRepository.save(producerToUpdate);
        return soundProducerDtoMapper.producerToProducerResDto(producerToUpdate);
    }

    @Override
    public SoundProducerResDto getProducerById(Integer id) {
        if(!soundProducerRepository.existsSoundProducerById(id))
            throw new NotFoundException("producer not found");
        return soundProducerDtoMapper.producerToProducerResDto(soundProducerRepository.getSoundProducerById(id).get());
    }

    @Override
    public SoundProducerResDto getProducerByNickname(String nickName) {
        if(!soundProducerRepository.existsSoundProducerByPersonInfoNickname(nickName))
            throw new NotFoundException("producer not found");
        return soundProducerDtoMapper.producerToProducerResDto(soundProducerRepository.getSoundProducerByPersonInfoNickname(nickName).get());
    }
}
