package com.genius.tms_c61_genius.service;

import com.genius.tms_c61_genius.mapper.ArtistDtoMapper;
import com.genius.tms_c61_genius.mapper.SongDtoMapper;
import com.genius.tms_c61_genius.model.domain.Artist;
import com.genius.tms_c61_genius.model.domain.Song;
import com.genius.tms_c61_genius.model.request.ArtistReqDto;
import com.genius.tms_c61_genius.model.request.UpdateArtistReqDto;
import com.genius.tms_c61_genius.model.response.ArtistResDto;
import com.genius.tms_c61_genius.model.response.SongResDto;
import com.genius.tms_c61_genius.repository.ArtistRepository;
import com.genius.tms_c61_genius.repository.SongRepository;
import com.genius.tms_c61_genius.service.ServiceImpl.ArtistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceTest {
    @Mock
    private ArtistRepository artistRepository;
    @Mock
    private SongRepository songRepository;
    @Mock
    private ArtistDtoMapper artistDtoMapper;
    @Mock
    private SongDtoMapper songDtoMapper;

    @InjectMocks
    private ArtistServiceImpl artistService;
    private Artist artist;
    private ArtistReqDto artistReqDto;
    private UpdateArtistReqDto updateArtistReqDto;
    private List<Song> songs;
    private List<SongResDto> artistSongs;
    private String nickname;
    @BeforeEach
    public void init() {
        artist = new Artist();
        nickname = "21 Savage";
        artistReqDto = new ArtistReqDto();
        songs = new ArrayList<>();
    }

    @Test
    public void saveArtistTest() {
        when(artistRepository.save(artist)).thenReturn(artist);
        ArtistResDto returned = artistService.createArtist(artistReqDto);
        verify(artistRepository).save(artist);
        assertEquals(artistDtoMapper.artistToArtistRes(artist), returned);
    }

    @Test
    public void getArtistSongsTest() {
        when(songRepository.getSongsByArtists(artistRepository.getArtistByPersonInfoNickname(nickname).get()))
                .thenReturn(songs);
        artistSongs = artistService.getSongs(nickname);
        verify(songRepository).getSongsByArtists(artistRepository.getArtistByPersonInfoNickname(nickname).get());
        assertEquals(songs.stream().map(s->songDtoMapper.songToSongRes(s)).toList(),artistSongs);
    }

    @Test
    public void getArtistTest(){
        when(artistRepository.getArtistByPersonInfoNickname(nickname)).thenReturn(Optional.of(artist));
        ArtistResDto returned = artistService.getArtistByNickName(nickname);
        verify(artistRepository).getArtistByPersonInfoNickname(nickname);
        assertEquals(artistDtoMapper.artistToArtistRes(artist), returned);
    }

    @Test
    public void updateArtist() {
        artist.setSongs(songs);
        when(artistRepository.getArtistByPersonInfoNickname(nickname)).thenReturn(Optional.of(artist));
        when(artistRepository.save(artist)).thenReturn(artist);
        artistService.updateArtist(updateArtistReqDto);
        verify(artistRepository).getArtistByPersonInfoNickname(nickname);
        verify(artistRepository).save(artist);
    }
}
