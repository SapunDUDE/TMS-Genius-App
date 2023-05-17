package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.mapper.ArtistDtoMapper;
import com.genius.tms_c61_genius.model.domain.Artist;
import com.genius.tms_c61_genius.model.request.ArtistReqDto;
import com.genius.tms_c61_genius.model.request.UpdateArtistReqDto;
import com.genius.tms_c61_genius.model.response.ArtistResDto;
import com.genius.tms_c61_genius.repository.ArtistRepository;
import com.genius.tms_c61_genius.repository.PersonInfoRepository;
import com.genius.tms_c61_genius.repository.UserRepository;
import com.genius.tms_c61_genius.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;
    private final ArtistDtoMapper artistDtoMapper;
    private final PersonInfoRepository personInfoRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository, UserRepository userRepository, ArtistDtoMapper artistDtoMapper, PersonInfoRepository personInfoRepository) {
        this.artistRepository = artistRepository;
        this.userRepository = userRepository;
        this.artistDtoMapper = artistDtoMapper;
        this.personInfoRepository = personInfoRepository;
    }

    @Override
    @Transactional
    public ArtistResDto createArtist(ArtistReqDto artistReqDto) {
        Artist savedArtist =  artistDtoMapper.artistReqToArtist(artistReqDto);
        artistRepository.save(savedArtist);
        return artistDtoMapper.artistToArtistRes(savedArtist);
    }

    @Override
    public ArtistResDto getArtistByUser(String userLogin) {
        return artistDtoMapper.artistToArtistRes(artistRepository.getArtistByUser_Login(userLogin));
    }

    @Override
    public ArtistResDto getArtistByNickName(String nickName) {
        return artistDtoMapper.artistToArtistRes(artistRepository.getArtistByPersonInfoNickname(nickName).get());
    }

    @Override
    @Transactional
    public void deleteArtistByUser(String userLogin) {
        artistRepository.deleteArtistByUser_Login(userLogin);
    }

    @Override
    @Transactional
    public void deleteArtistByNickName(String nickName) {
        artistRepository.deleteArtistByPersonInfoNickname(nickName);
    }

    @Override
    @Transactional
    public ArtistResDto updateArtist(UpdateArtistReqDto updateArtistReqDto) {
         Artist artistToUpdate = artistRepository.getArtistByPersonInfoNickname(updateArtistReqDto.getNickname()).get();
         artistDtoMapper.updateArtistInfo(artistToUpdate,updateArtistReqDto);
         artistRepository.save(artistToUpdate);
         return artistDtoMapper.artistToArtistRes(artistToUpdate);
    }
}
