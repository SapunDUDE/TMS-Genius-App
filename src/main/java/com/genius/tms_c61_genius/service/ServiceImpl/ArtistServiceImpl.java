package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.exception.BadDataException;
import com.genius.tms_c61_genius.exception.NotFoundException;
import com.genius.tms_c61_genius.mapper.ArtistDtoMapper;
import com.genius.tms_c61_genius.mapper.SongDtoMapper;
import com.genius.tms_c61_genius.model.domain.Artist;
import com.genius.tms_c61_genius.model.request.ArtistReqDto;
import com.genius.tms_c61_genius.model.request.UpdateArtistReqDto;
import com.genius.tms_c61_genius.model.response.ArtistResDto;
import com.genius.tms_c61_genius.model.response.SongResDto;
import com.genius.tms_c61_genius.repository.AlbumRepository;
import com.genius.tms_c61_genius.repository.ArtistRepository;
import com.genius.tms_c61_genius.repository.SongRepository;
import com.genius.tms_c61_genius.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistDtoMapper artistDtoMapper;
    private final SongRepository songRepository;
    private final SongDtoMapper songDtoMapper;
    private final AlbumRepository albumRepository;
    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository,
                             ArtistDtoMapper artistDtoMapper,
                             SongRepository songRepository,
                             SongDtoMapper songDtoMapper,
                             AlbumRepository albumRepository) {
        this.artistRepository = artistRepository;
        this.artistDtoMapper = artistDtoMapper;
        this.songRepository = songRepository;
        this.songDtoMapper = songDtoMapper;
        this.albumRepository = albumRepository;
    }

    @Override
    @Transactional
    public ArtistResDto createArtist(ArtistReqDto artistReqDto) {
        if(artistRepository.existsArtistByPersonInfoNickname(artistReqDto.getNickname())) {
            throw new BadDataException("artist with such nickname is already exist");
        }
        Artist savedArtist =  artistDtoMapper.artistReqToArtist(artistReqDto);
        artistRepository.save(savedArtist);
        return artistDtoMapper.artistToArtistRes(savedArtist);
    }

    @Override
    public ArtistResDto getArtistByUser(String userLogin) {
        if(!artistRepository.existsArtistByUserLogin(userLogin)) {
            throw new NotFoundException("artist not found");
        }
        return artistDtoMapper.artistToArtistRes(artistRepository.getArtistByUser_Login(userLogin));
    }

    @Override
    public ArtistResDto getArtistByNickName(String nickName) {
        if(!artistRepository.existsArtistByPersonInfoNickname(nickName)) {
            throw new NotFoundException("artist not found");
        }
        return artistDtoMapper.artistToArtistRes(artistRepository.getArtistByPersonInfoNickname(nickName).get());
    }

    @Override
    public String getUserLogin(String nickName) {
        if(!artistRepository.existsArtistByPersonInfoNickname(nickName)) {
            throw new NotFoundException("artist not found");
        }
        if(artistRepository.getArtistByPersonInfoNickname(nickName).get().getUser()==null){
            throw new NotFoundException("artist with such user not found");
        }
        return artistRepository.getArtistByPersonInfoNickname(nickName).get().getUser().getLogin();
    }

    @Override
    @Transactional
    public void deleteArtistByUser(String userLogin) {
        if(!artistRepository.existsArtistByUserLogin(userLogin)) {
            throw new NotFoundException("artist not found");
        }
        artistRepository.deleteArtistByUser_Login(userLogin);
    }

    @Override
    @Transactional
    public void deleteArtistByNickName(String nickName) {
        if(!artistRepository.existsArtistByPersonInfoNickname(nickName)) {
            throw new NotFoundException("artist not found");
        }
        artistRepository.deleteArtistByPersonInfoNickname(nickName);
    }

    @Override
    @Transactional
    public ArtistResDto updateArtist(UpdateArtistReqDto updateArtistReqDto) {
        if(artistRepository.existsArtistByPersonInfoNickname(updateArtistReqDto.getNickname())) {
            throw new BadDataException("artist with such nickname is already exist");
        }
         Artist artistToUpdate = artistRepository.getArtistByPersonInfoNickname(updateArtistReqDto.getNickname()).get();
         artistDtoMapper.updateArtistInfo(artistToUpdate,updateArtistReqDto);
         artistRepository.save(artistToUpdate);
         return artistDtoMapper.artistToArtistRes(artistToUpdate);
    }

    @Override
    public List<SongResDto> getSongs(String nickName) {
        if(!artistRepository.existsArtistByPersonInfoNickname(nickName)) {
            throw new NotFoundException("artist not found");
        }
        return songRepository.getSongsByArtists(artistRepository.getArtistByPersonInfoNickname(nickName).get())
                .stream()
                .map(song-> songDtoMapper.songToSongRes(song))
                .toList();
    }

    @Override
    public List<String> getAlbums(String nickName) {
        if(!artistRepository.existsArtistByPersonInfoNickname(nickName)) {
            throw new NotFoundException("artist not found");
        }
       return albumRepository.findAlbumsByArtists(artistRepository.getArtistByPersonInfoNickname(nickName).get())
               .stream()
               .map(album->album.getAlbumTitle())
               .toList();
    }
}
