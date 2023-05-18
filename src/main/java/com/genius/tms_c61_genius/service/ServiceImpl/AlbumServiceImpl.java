package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.mapper.AlbumDtoMapper;
import com.genius.tms_c61_genius.mapper.ArtistDtoMapper;
import com.genius.tms_c61_genius.mapper.SongDtoMapper;
import com.genius.tms_c61_genius.model.request.AlbumReqDto;
import com.genius.tms_c61_genius.model.response.AlbumResDto;
import com.genius.tms_c61_genius.model.response.SongResDto;
import com.genius.tms_c61_genius.repository.*;
import com.genius.tms_c61_genius.model.domain.Album;
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
                            GenreRepository genreRepository,
                            AlbumTypeRepository albumTypeRepository,
                            LabelRepository labelRepository,
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
        return songRepository.getSongsByAlbum_AlbumTitle(albumTitle)
                .stream().map(song-> songDtoMapper.songToSongRes(song))
                .toList();

    }

    @Override
    public List<String> getProducers(String albumTitle) {
        return soundProducerRepository.getSoundProducersByAlbums(
                albumRepository.getAlbumByAlbumTitle(albumTitle))
                .stream().map(producer->producer.getPersonInfo().getNickname())
                .toList();
    }

    @Override
    public List<String> getArtists(String albumTitle) {
        return artistRepository.getArtistByAlbums(albumRepository.getAlbumByAlbumTitle(albumTitle))
                .stream().map(artist->artist.getPersonInfo().getNickname())
                .toList();
    }

    @Override
    public AlbumResDto getAlbumByTitle(String albumTitle) {
        return albumDtoMapper.albumToAlbumRes(albumRepository.getAlbumByAlbumTitle(albumTitle));
    }

    @Override
    @Transactional
    public void deleteAlbum(String albumTitle) {
        songRepository.deleteSongsByAlbumAlbumTitle(albumTitle);
        albumRepository.delete(albumRepository.getAlbumByAlbumTitle(albumTitle));
    }

    @Override
    @Transactional
    public AlbumResDto createAlbum(AlbumReqDto albumReqDto) {
        Album newAlbum = albumDtoMapper.albumReqToAlbum(albumReqDto);
        return albumDtoMapper.albumToAlbumRes(newAlbum);
    }
}
