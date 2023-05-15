package com.genius.tms_c61_genius.service;

import com.genius.tms_c61_genius.model.request.ArtistReqDto;
import com.genius.tms_c61_genius.model.response.ArtistResDto;


public interface ArtistService {
    ArtistResDto createArtist(ArtistReqDto artistReqDto);
}
