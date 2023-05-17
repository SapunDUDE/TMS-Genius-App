package com.genius.tms_c61_genius.mapper;

import com.genius.tms_c61_genius.model.domain.PersonInfo;

import com.genius.tms_c61_genius.model.domain.SoundProducer;
import com.genius.tms_c61_genius.model.request.SoundProducerReqDto;
import com.genius.tms_c61_genius.model.response.SoundProducerResDto;
import org.springframework.stereotype.Component;

@Component
public class SoundProducerDtoMapper {

    public SoundProducer producerReqToProducer(SoundProducerReqDto soundProducerReqDto){
       return SoundProducer.builder()
               .personInfo(PersonInfo.builder()
                       .firstName(soundProducerReqDto.getFirstName())
                       .lastName(soundProducerReqDto.getLastName())
                       .nickname(soundProducerReqDto.getNickname())
                       .bio(soundProducerReqDto.getBio())
                       .birthDate(soundProducerReqDto.getBirthDate())
                       .country(soundProducerReqDto.getCountry())
                       .build())
               .build();

    }

    public SoundProducerResDto producerToProducerResDto(SoundProducer soundProducer){
        return SoundProducerResDto.builder()
                .firstName(soundProducer.getPersonInfo().getFirstName())
                .lastName(soundProducer.getPersonInfo().getLastName())
                .nickname(soundProducer.getPersonInfo().getNickname())
                .bio(soundProducer.getPersonInfo().getBio())
                .birthDate(soundProducer.getPersonInfo().getBirthDate())
                .country(soundProducer.getPersonInfo().getCountry())
                .build();
    }

    public SoundProducer updateProducerInfo(SoundProducer soundProducerToUpdate,SoundProducerReqDto soundProducerReqDto){
        if(soundProducerReqDto.getFirstName()!=null){
            soundProducerToUpdate.getPersonInfo().setFirstName(soundProducerReqDto.getFirstName());
        }
        if(soundProducerReqDto.getLastName()!=null){
            soundProducerToUpdate.getPersonInfo().setLastName(soundProducerReqDto.getLastName());
        }
        if(soundProducerReqDto.getBio()!=null){
            soundProducerToUpdate.getPersonInfo().setBio(soundProducerReqDto.getBio());
        }
        if(soundProducerReqDto.getBirthDate()!=null){
            soundProducerToUpdate.getPersonInfo().setBirthDate(soundProducerReqDto.getBirthDate());
        }
        if(soundProducerReqDto.getCountry()!=null){
            soundProducerToUpdate.getPersonInfo().setCountry(soundProducerReqDto.getCountry());
        }
        return soundProducerToUpdate;
    }
}
