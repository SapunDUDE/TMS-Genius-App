package com.genius.tms_c61_genius.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.genius.tms_c61_genius.model.request.ArtistReqDto;
import com.genius.tms_c61_genius.model.response.ArtistResDto;
import com.genius.tms_c61_genius.model.response.SongResDto;
import com.genius.tms_c61_genius.service.ArtistService;
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
public class ArtistControllerTest {
    private MockMvc mockMvc;
    private ObjectWriter objectWriter;

    @Mock
    ArtistService artistService;

    @InjectMocks
    private ArtistController artistController;
    private ArtistReqDto artistReqDto;
    private ArtistResDto artistResDto;
    private String nickname;
    private List<SongResDto> songs;


    @BeforeEach
    public void init() {
        objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        nickname = "sapunchik";
        artistReqDto = new ArtistReqDto("Ivan","Smolov","Kolbaser","Ochen horosh",new Date(2003-10-12),"Belarus","sapun");
        artistResDto = new ArtistResDto();
        songs = new ArrayList<>();

        mockMvc = MockMvcBuilders
                .standaloneSetup(artistController)
                .build();
    }

    @Test
    public void createArtistTest() throws Exception{
        when(artistService.createArtist(artistReqDto)).thenReturn(artistResDto);
        mockMvc.perform(post("/artist")
                        .contentType(APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(artistReqDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        verify(artistService, times(1)).createArtist(artistReqDto);
    }

    @Test
    public void deleteArtistTest() throws Exception {
        mockMvc.perform(delete("/artist/{nickname}",nickname))
                .andExpect(status().isNoContent())
                .andReturn();
        verify(artistService).deleteArtistByNickName(nickname);
    }

    @Test
    public void getArtistByNicknameTest() throws Exception {
        when(artistService.getArtistByNickName(nickname)).thenReturn(artistResDto);
        mockMvc.perform(get("/artist/{nickname}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        verify(artistService, times(1)).getArtistByNickName(nickname);
    }

    @Test
    public void getArtistSongsTest() throws Exception {
        when(artistService.getSongs(nickname)).thenReturn(songs);
        mockMvc.perform(get("/artist/songs/{nickname}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        verify(artistService, times(1)).getSongs(nickname);
    }

}
