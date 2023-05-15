package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.mapper.ArtistDtoMapper;
import com.genius.tms_c61_genius.model.domain.Artist;
import com.genius.tms_c61_genius.model.request.ArtistReqDto;
import com.genius.tms_c61_genius.model.response.ArtistResDto;
import com.genius.tms_c61_genius.repository.ArtistRepository;
import com.genius.tms_c61_genius.repository.PersonInfoRepository;
import com.genius.tms_c61_genius.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistDtoMapper artistDtoMapper;
    private final PersonInfoRepository personInfoRepository;
    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository, ArtistDtoMapper artistDtoMapper, PersonInfoRepository personInfoRepository) {
        this.artistRepository = artistRepository;
        this.artistDtoMapper = artistDtoMapper;
        this.personInfoRepository = personInfoRepository;
    }

    @Override
    @Transactional
    public ArtistResDto createArtist(ArtistReqDto artistReqDto) {
        Artist savedArtist =  artistDtoMapper.artistReqToArtist(artistReqDto);
        personInfoRepository.save(savedArtist.getPersonInfo());
        artistRepository.save(savedArtist);
        return artistDtoMapper.artistToArtistRes(savedArtist);
    }
}
