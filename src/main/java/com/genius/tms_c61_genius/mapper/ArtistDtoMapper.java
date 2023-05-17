package com.genius.tms_c61_genius.mapper;


import com.genius.tms_c61_genius.model.domain.PersonInfo;
import com.genius.tms_c61_genius.model.domain.User;
import com.genius.tms_c61_genius.model.request.ArtistReqDto;
import com.genius.tms_c61_genius.model.request.UpdateArtistReqDto;
import com.genius.tms_c61_genius.model.response.ArtistResDto;
import com.genius.tms_c61_genius.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.genius.tms_c61_genius.model.domain.Artist;

import java.sql.Date;
import java.util.LinkedList;

@Component
public class ArtistDtoMapper {
    private final UserRepository userRepository;
    @Autowired
    public ArtistDtoMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Artist artistReqToArtist(ArtistReqDto artistReqDto){
        return(Artist.builder().
                user((userRepository.getUserByLogin(artistReqDto.getUserLogin()).isPresent())?
                        userRepository.getUserByLogin(artistReqDto.getUserLogin()).get():null)
                .songs(null)
                .albums(null)
                .personInfo(PersonInfo.builder().
                        firstName(artistReqDto.getFirstName())
                        .lastName(artistReqDto.getLastName())
                        .nickname(artistReqDto.getNickname())
                        .bio(artistReqDto.getBio())
                        .birthDate(artistReqDto.getBirthDate())
                        .country(artistReqDto.getCountry())
                        .build())
                .build());
    }

    public ArtistResDto artistToArtistRes(Artist artist){
        return ArtistResDto.builder()
                .firstName(artist.getPersonInfo().getFirstName())
                .lastName(artist.getPersonInfo().getLastName())
                .nickname(artist.getPersonInfo().getNickname())
                .bio(artist.getPersonInfo().getBio())
                .birthDate(artist.getPersonInfo().getBirthDate())
                .country(artist.getPersonInfo().getCountry())
                .build();
    }

    public Artist updateArtistInfo(Artist oldArtist, UpdateArtistReqDto newArtist){
        if(newArtist.getFirstName()!=null){
            oldArtist.getPersonInfo().setFirstName(newArtist.getFirstName());
        }
        if(newArtist.getLastName()!=null){
            oldArtist.getPersonInfo().setLastName(newArtist.getLastName());
        }
        if(newArtist.getBio()!=null){
            oldArtist.getPersonInfo().setBio(newArtist.getBio());
        }
        if(newArtist.getBirthDate()!=null){
            oldArtist.getPersonInfo().setBirthDate(newArtist.getBirthDate());
        }
        if(newArtist.getCountry()!=null){
            oldArtist.getPersonInfo().setCountry(newArtist.getCountry());
        }
        return oldArtist;
    }
}
