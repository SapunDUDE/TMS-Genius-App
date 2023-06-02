package com.genius.tms_c61_genius.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.genius.tms_c61_genius.model.request.AlbumReqDto;
import com.genius.tms_c61_genius.model.request.SongReqDto;
import com.genius.tms_c61_genius.model.response.AlbumResDto;
import com.genius.tms_c61_genius.model.response.SongResDto;
import com.genius.tms_c61_genius.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AlbumControllerTest {
    private MockMvc mockMvc;
    private ObjectWriter objectWriter;

    @Mock
    AlbumService albumService;
    AlbumReqDto albumReqDto;
    AlbumResDto albumResDto;
    String title;
    List<SongReqDto> songs;
    List<String> artists;
    List<String> producers;
    List<SongResDto> albumSongs;
    List<String> albumProducers;
    List<String> albumArtists;

    @InjectMocks
    private AlbumController albumController;

    @BeforeEach
    public void init() {
        objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        title = "No Advance";
        songs = new ArrayList<>();
        artists = new ArrayList<>();
        producers = new ArrayList<>();
        albumSongs = new ArrayList<>();
        albumProducers = new ArrayList<>();
        albumArtists = new ArrayList<>();
        songs.add(new SongReqDto("Issa","hey hey hey",270));
        artists.add("Miladze");
        artists.add("Kabzon");
        producers.add("metro boomin");

        albumReqDto = new AlbumReqDto(title, new Date(2019 - 10 - 11),"SINGLE","SONY","HIP_HOP",songs,artists,producers);
        albumResDto = new AlbumResDto();

        mockMvc = MockMvcBuilders
                .standaloneSetup(albumController)
                .build();
    }
    @Test
    public void createAlbumTest() throws Exception{
        when(albumService.createAlbum(albumReqDto)).thenReturn(albumResDto);
        mockMvc.perform(post("/album")
                        .contentType(APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(albumReqDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        verify(albumService, times(1)).createAlbum(albumReqDto);
    }

    @Test
    public void deleteAlbumTest() throws Exception {
        mockMvc.perform(delete("/album/{title}",title))
                .andExpect(status().isNoContent())
                .andReturn();
        verify(albumService).deleteAlbum(title);
    }

    @Test
    public void getAlbumTest() throws Exception {
        when(albumService.getAlbumByTitle(title)).thenReturn(albumResDto);
        mockMvc.perform(get("/album/{title}",title))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        verify(albumService, times(1)).getAlbumByTitle(title);
    }

    @Test
    public void getAlbumSongsTest() throws Exception {
        when(albumService.getSongs(title)).thenReturn(albumSongs);
        mockMvc.perform(get("/album/songs/{title}",title))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        verify(albumService, times(1)).getSongs(title);
    }
    @Test
    public void getAlbumProducersTest() throws Exception {
        when(albumService.getProducers(title)).thenReturn(albumProducers);
        mockMvc.perform(get("/album/producers/{title}",title))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        verify(albumService, times(1)).getProducers(title);
    }
    @Test
    public void getAlbumArtistsTest() throws Exception {
        when(albumService.getArtists(title)).thenReturn(albumArtists);
        mockMvc.perform(get("/album/artists/{title}",title))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        verify(albumService, times(1)).getArtists(title);
    }
}
