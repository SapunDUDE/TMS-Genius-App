package com.genius.tms_c61_genius.service;

import com.genius.tms_c61_genius.model.request.ArtistReqDto;
import com.genius.tms_c61_genius.model.request.UpdateArtistReqDto;
import com.genius.tms_c61_genius.model.response.ArtistResDto;


public interface ArtistService {
    ArtistResDto createArtist(ArtistReqDto artistReqDto);

    void deleteArtistByUser(String userLogin);
    void deleteArtistByNickName(String nickName);
    ArtistResDto updateArtist(UpdateArtistReqDto artistReqDto);

    ArtistResDto getArtistByNickName(String nickName);
    ArtistResDto getArtistByUser(String userLogin);
}
