package com.genius.tms_c61_genius.mapper;

import com.genius.tms_c61_genius.model.domain.Album;
import com.genius.tms_c61_genius.model.domain.Artist;
import com.genius.tms_c61_genius.model.domain.Song;
import com.genius.tms_c61_genius.model.domain.SoundProducer;
import com.genius.tms_c61_genius.model.request.AlbumReqDto;
import com.genius.tms_c61_genius.model.request.SongReqDto;
import com.genius.tms_c61_genius.model.response.AlbumResDto;
import com.genius.tms_c61_genius.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Component
public class AlbumDtoMapper {
    private final SongRepository songRepository;
    private final GenreRepository genreRepository;
    private final AlbumTypeRepository albumTypeRepository;
    private final LabelRepository labelRepository;
    private final SoundProducerRepository soundProducerRepository;
    private final ArtistRepository artistRepository;
    private final SongDtoMapper songDtoMapper;
    private final AlbumRepository albumRepository;
    @Autowired
    public AlbumDtoMapper(SongRepository songRepository,
                          GenreRepository genreRepository,
                          AlbumTypeRepository albumTypeRepository,
                          LabelRepository labelRepository,
                          SoundProducerRepository soundProducerRepository,
                          ArtistRepository artistRepository,
                          SongDtoMapper songDtoMapper,
                          AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.genreRepository = genreRepository;
        this.albumTypeRepository = albumTypeRepository;
        this.labelRepository = labelRepository;
        this.soundProducerRepository = soundProducerRepository;
        this.artistRepository = artistRepository;
        this.songDtoMapper = songDtoMapper;
        this.albumRepository = albumRepository;
    }

    public Album albumReqToAlbum(AlbumReqDto albumReqDto){
        List<SoundProducer> producers =  albumReqDto.getProducerNickNames()
                .stream()
                .filter(nick->{
                    if(soundProducerRepository.getSoundProducerByPersonInfoNickname(nick).isPresent()){
                        return true;
                    }
                    return false;
                })
                .map(nick -> {
                    return soundProducerRepository.getSoundProducerByPersonInfoNickname(nick).get();
                })
                .toList();

        List<Artist> artists = albumReqDto.getArtistNickNames()
                .stream()
                .filter(nick->{
                    if(artistRepository.getArtistByPersonInfoNickname(nick).isPresent()){
                        return true;
                    }
                    return false;
                })
                .map(nick -> {
                    return artistRepository.getArtistByPersonInfoNickname(nick).get();
                })
                .toList();

        Album newAlbum = Album.builder()
                .albumTitle(albumReqDto.getAlbumTitle())
                .created(albumReqDto.getCreated())
                .genre(genreRepository.findGenreByGenreTitle(albumReqDto.getGenre()))
                .albumType(albumTypeRepository.findAlbumTypeByTypeName(albumReqDto.getAlbumType()))
                .label(labelRepository.getLabelByLabelName(albumReqDto.getLabel()))
                .soundProducers(producers)
                .artists(artists)
                .build();
        albumRepository.save(newAlbum);

        List<Song> songs = albumReqDto.getSongs()
                .stream()
                .map(newSong-> songRepository.save(songDtoMapper.songReqToSong(newSong,artists,newAlbum))
                )
                .toList();

        return newAlbum;
    }
    public AlbumResDto albumToAlbumRes(Album album){
        return AlbumResDto.builder()
                .albumTitle(album.getAlbumTitle())
                .created(album.getCreated())
                .albumType(album.getAlbumType().getTypeName())
                .label(album.getLabel().getLabelName())
                .genre(album.getGenre().getGenreTitle())
                .build();
    }
}
