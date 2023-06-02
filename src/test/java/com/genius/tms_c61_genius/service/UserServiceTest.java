package com.genius.tms_c61_genius.service;

import com.genius.tms_c61_genius.mapper.CommentDtoMapper;
import com.genius.tms_c61_genius.mapper.UserDtoMapper;
import com.genius.tms_c61_genius.model.domain.Comment;
import com.genius.tms_c61_genius.model.domain.User;
import com.genius.tms_c61_genius.model.request.UserReqDto;
import com.genius.tms_c61_genius.model.response.CommentResDto;
import com.genius.tms_c61_genius.model.response.UserResDto;
import com.genius.tms_c61_genius.repository.CommentRepository;
import com.genius.tms_c61_genius.repository.UserRepository;
import com.genius.tms_c61_genius.service.ServiceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private CommentDtoMapper commentDtoMapper;
    @Mock
    private UserDtoMapper userDtoMapper;

    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    private UserReqDto userReqDto;
    private List<Comment> comments;
    private String login;
    Integer id;

    @BeforeEach
    public void init() {
        id = 1;
        user = new User();
        login = "sapun";
        userReqDto = new UserReqDto();
        comments = new ArrayList<>();
    }

    @Test
    public void saveUserTest() {
        when(userRepository.save(user)).thenReturn(user);
        UserResDto returned = userService.createUser(userReqDto);
        verify(userRepository).save(user);
        assertEquals(userDtoMapper.userToUserRes(user), returned);
    }
    @Test
    public void getUserTest() {
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        UserResDto returned = userService.getUser(login);
        verify(userRepository).findById(id);
        assertEquals(userDtoMapper.userToUserRes(user), returned);
    }

    @Test
    public void getCommentsTest() {
        when(commentRepository.getCommentsByUserId(id)).thenReturn(comments);
        List<CommentResDto> returnedComments = userService.getComments(id);
        verify(commentRepository).getCommentsByUserId(id);
        assertEquals(comments.stream().map(c -> commentDtoMapper.commentToCommentRes(c)).toList(), returnedComments);
    }

}
