package com.genius.tms_c61_genius.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.genius.tms_c61_genius.model.request.UserReqDto;
import com.genius.tms_c61_genius.model.response.UserResDto;
import com.genius.tms_c61_genius.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    private MockMvc mockMvc;
    private ObjectWriter objectWriter;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;
    private UserReqDto userValidReqDto;
    private UserReqDto userNotValidReqDto;
    private UserResDto userResDto;
    private String login;

    @BeforeEach
    public void init() {
        objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        login = "sapunchik";
        userValidReqDto = new UserReqDto("dimakoko","dimasik15","helloHi12","USER");
        userNotValidReqDto = new UserReqDto("asasas","1","2","USER");
        userResDto = new UserResDto();
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    public void createValidUserTest() throws Exception{
        when(userService.createUser(userValidReqDto)).thenReturn(userResDto);
        mockMvc.perform(post("/user/registration")
                .contentType(APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(userValidReqDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        verify(userService, times(1)).createUser(userValidReqDto);
    }

    @Test
    public void createNotValidUserTest() throws Exception{
        when(userService.createUser(userNotValidReqDto)).thenReturn(userResDto);
        mockMvc.perform(post("/user/registration")
                        .contentType(APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(userNotValidReqDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/user/{login}",login))
                .andExpect(status().isNoContent())
                .andReturn();
        verify(userService).deleteUser(login);
    }

}
