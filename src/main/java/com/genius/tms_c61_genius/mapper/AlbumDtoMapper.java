package com.genius.tms_c61_genius.mapper;

import com.genius.tms_c61_genius.model.domain.Album;
import com.genius.tms_c61_genius.model.domain.Artist;
import com.genius.tms_c61_genius.model.domain.Song;
import com.genius.tms_c61_genius.model.domain.SoundProducer;
import com.genius.tms_c61_genius.model.request.AlbumReqDto;
import com.genius.tms_c61_genius.model.response.AlbumResDto;
import com.genius.tms_c61_genius.repository.AlbumRepository;
import com.genius.tms_c61_genius.repository.AlbumTypeRepository;
import com.genius.tms_c61_genius.repository.ArtistRepository;
import com.genius.tms_c61_genius.repository.GenreRepository;
import com.genius.tms_c61_genius.repository.LabelRepository;
import com.genius.tms_c61_genius.repository.SongRepository;
import com.genius.tms_c61_genius.repository.SoundProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                .map(nick ->
                    soundProducerRepository.getSoundProducerByPersonInfoNickname(nick).get()
                )
                .toList();

        List<Artist> artists = albumReqDto.getArtistNickNames()
                .stream()
                .filter(nick->{
                    if(artistRepository.getArtistByPersonInfoNickname(nick).isPresent()){
                        return true;
                    }
                    return false;
                })
                .map(nick ->
                    artistRepository.getArtistByPersonInfoNickname(nick).get()
                )
                .toList();

        List<Song> songs = albumReqDto.getSongs()
                .stream()
                .map(newSong-> songDtoMapper.songReqToSong(newSong,artists)
                )
                .toList();

        Album newAlbum = Album.builder()
                .albumTitle(albumReqDto.getAlbumTitle())
                .created(albumReqDto.getCreated())
                .genre(genreRepository.findGenreByGenreTitle(albumReqDto.getGenre()).get())
                .albumType(albumTypeRepository.findAlbumTypeByTypeName(albumReqDto.getAlbumType()).get())
                .label(labelRepository.getLabelByLabelName(albumReqDto.getLabel()).get())
                .soundProducers(producers)
                .artists(artists)
                .songs(songRepository.saveAll(songs))
                .build();

        return albumRepository.save(newAlbum);
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
