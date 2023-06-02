package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.exception.BadDataException;
import com.genius.tms_c61_genius.mapper.AlbumDtoMapper;
import com.genius.tms_c61_genius.mapper.SongDtoMapper;
import com.genius.tms_c61_genius.model.request.AlbumReqDto;
import com.genius.tms_c61_genius.model.response.AlbumResDto;
import com.genius.tms_c61_genius.model.response.SongResDto;
import com.genius.tms_c61_genius.model.domain.Album;
import com.genius.tms_c61_genius.repository.AlbumRepository;
import com.genius.tms_c61_genius.repository.ArtistRepository;
import com.genius.tms_c61_genius.repository.SongRepository;
import com.genius.tms_c61_genius.repository.SoundProducerRepository;
import com.genius.tms_c61_genius.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumDtoMapper albumDtoMapper;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final SoundProducerRepository soundProducerRepository;
    private final SongDtoMapper songDtoMapper;
    private final ArtistRepository artistRepository;

    @Autowired
    public AlbumServiceImpl(AlbumDtoMapper albumDtoMapper,
                            AlbumRepository albumRepository,
                            SongRepository songRepository,
                            SoundProducerRepository soundProducerRepository,
                            SongDtoMapper songDtoMapper,
                            ArtistRepository artistRepository) {
        this.albumDtoMapper = albumDtoMapper;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.soundProducerRepository = soundProducerRepository;
        this.songDtoMapper = songDtoMapper;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<SongResDto> getSongs(String albumTitle) {
        if(!albumRepository.existsAlbumByAlbumTitle(albumTitle)){
            throw new BadDataException("album not found");
        }

        return songRepository.getSongsByAlbum_AlbumTitle(albumTitle)
                .stream().map(song-> songDtoMapper.songToSongRes(song))
                .toList();

    }

    @Override
    public List<String> getProducers(String albumTitle) {
        if(!albumRepository.existsAlbumByAlbumTitle(albumTitle)) {
            throw new BadDataException("album not found");
        }
        return soundProducerRepository.getSoundProducersByAlbums(
                albumRepository.getAlbumByAlbumTitle(albumTitle).get())
                .stream().map(producer->producer.getPersonInfo().getNickname())
                .toList();
    }

    @Override
    public List<String> getArtists(String albumTitle) {
        if(!albumRepository.existsAlbumByAlbumTitle(albumTitle)) {
            throw new BadDataException("album not found");
        }
        return artistRepository.getArtistByAlbums(albumRepository.getAlbumByAlbumTitle(albumTitle).get())
                .stream().map(artist->artist.getPersonInfo().getNickname())
                .toList();
    }

    @Override
    public AlbumResDto getAlbumByTitle(String albumTitle) {
        if(!albumRepository.existsAlbumByAlbumTitle(albumTitle)) {
            throw new BadDataException("album not found");
        }
        return albumDtoMapper.albumToAlbumRes(albumRepository.getAlbumByAlbumTitle(albumTitle).get());
    }

    @Override
    @Transactional
    public void deleteAlbum(String albumTitle) {
        if(!albumRepository.existsAlbumByAlbumTitle(albumTitle)) {
            throw new BadDataException("album not found");
        }
        albumRepository.delete(albumRepository.getAlbumByAlbumTitle(albumTitle).get());
    }

    @Override
    @Transactional
    public AlbumResDto createAlbum(AlbumReqDto albumReqDto) {
        if(albumRepository.existsAlbumByAlbumTitle(albumReqDto.getAlbumTitle())) {
            throw new BadDataException("album with such title is already exist");
        }
        Album newAlbum = albumDtoMapper.albumReqToAlbum(albumReqDto);
        return albumDtoMapper.albumToAlbumRes(newAlbum);
    }

    @Override
    public List<String> getUsers(List<String> artistNickNames) {
        return artistNickNames.stream().filter(nick->artistRepository.getArtistByPersonInfoNickname(nick).isPresent()?true:false)
                .map(nick->artistRepository.getArtistByPersonInfoNickname(nick).get())
                .filter(artist->artist.getUser()!=null?true:false)
                .map(artist -> artist.getUser().getLogin())
                .toList();
    }
}
