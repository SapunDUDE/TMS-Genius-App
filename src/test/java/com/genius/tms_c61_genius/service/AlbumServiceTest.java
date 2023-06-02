package com.genius.tms_c61_genius.service;

import com.genius.tms_c61_genius.mapper.AlbumDtoMapper;
import com.genius.tms_c61_genius.mapper.SongDtoMapper;
import com.genius.tms_c61_genius.model.domain.Album;
import com.genius.tms_c61_genius.model.domain.Artist;
import com.genius.tms_c61_genius.model.domain.Song;
import com.genius.tms_c61_genius.model.domain.SoundProducer;
import com.genius.tms_c61_genius.model.request.AlbumReqDto;
import com.genius.tms_c61_genius.model.response.AlbumResDto;
import com.genius.tms_c61_genius.model.response.SongResDto;
import com.genius.tms_c61_genius.repository.AlbumRepository;
import com.genius.tms_c61_genius.repository.ArtistRepository;
import com.genius.tms_c61_genius.repository.SongRepository;
import com.genius.tms_c61_genius.repository.SoundProducerRepository;
import com.genius.tms_c61_genius.service.ServiceImpl.AlbumServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlbumServiceTest {
    @Mock
    private AlbumRepository albumRepository;
    @Mock
    private SongRepository songRepository;
    @Mock
    private SoundProducerRepository soundProducerRepository;
    @Mock
    private SongDtoMapper songDtoMapper;
    @Mock
    private AlbumDtoMapper albumDtoMapper;
    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private AlbumServiceImpl albumService;
    private Album album;
    private String title;
    private AlbumReqDto albumReqDto;
    private List<Song> songs;
    private List<SoundProducer> soundProducers;
    private List<Artist> artists;

    @BeforeEach
    public void init() {
        album = new Album();
        title = "No Advance";
        albumReqDto = new AlbumReqDto();
        songs = new ArrayList<>();
        soundProducers = new ArrayList<>();
        artists = new ArrayList<>();
    }

    @Test
    public void saveAlbumTest() {
        when(albumRepository.save(album)).thenReturn(album);
        AlbumResDto returned = albumService.createAlbum(albumReqDto);
        verify(albumRepository).save(album);
        assertEquals(albumDtoMapper.albumToAlbumRes(album), returned);
    }

    @Test
    public void getAlbumTest(){
        when(albumRepository.getAlbumByAlbumTitle(title).get()).thenReturn(album);
        AlbumResDto returned = albumService.getAlbumByTitle(title);
        verify(albumRepository).getAlbumByAlbumTitle(title);
        assertEquals(albumDtoMapper.albumToAlbumRes(album), returned);
    }

    @Test
    public void getAlbumSongsTest(){
        when(songRepository.getSongsByAlbum_AlbumTitle(title)).thenReturn(songs);
        List<SongResDto> returnedSongs = albumService.getSongs(title);
        verify(songRepository).getSongsByAlbum_AlbumTitle(title);
        assertEquals(songs.stream().map(s->songDtoMapper.songToSongRes(s)).toList(), returnedSongs);
    }

    @Test
    public void getAlbumProducersTest(){
        when(soundProducerRepository.getSoundProducersByAlbums(albumRepository.getAlbumByAlbumTitle(title).get())).thenReturn(soundProducers);
        List<String> returnedProducers = albumService.getProducers(title);
        verify(soundProducerRepository).getSoundProducersByAlbums(albumRepository.getAlbumByAlbumTitle(title).get());
        assertEquals(soundProducers.stream().map(p->p.getPersonInfo().getNickname()).toList(), returnedProducers);
    }

    @Test
    public void getAlbumArtistsTest(){
        when(artistRepository.getArtistByAlbums(albumRepository.getAlbumByAlbumTitle(title).get())).thenReturn(artists);
        List<String> returnedArtists = albumService.getArtists(title);
        verify(artistRepository).getArtistByAlbums(albumRepository.getAlbumByAlbumTitle(title).get());
        assertEquals(artists.stream().map(a->a.getPersonInfo().getNickname()).toList(), returnedArtists);
    }

}
