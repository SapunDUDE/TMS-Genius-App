package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.exception.BadDataException;
import com.genius.tms_c61_genius.exception.NotFoundException;
import com.genius.tms_c61_genius.mapper.CommentDtoMapper;
import com.genius.tms_c61_genius.mapper.UserDtoMapper;
import com.genius.tms_c61_genius.model.domain.User;
import com.genius.tms_c61_genius.model.request.UserReqDto;
import com.genius.tms_c61_genius.model.response.CommentResDto;
import com.genius.tms_c61_genius.model.response.UserResDto;
import com.genius.tms_c61_genius.repository.CommentRepository;
import com.genius.tms_c61_genius.repository.UserRepository;
import com.genius.tms_c61_genius.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    //TODO add password encoder and custom exceptions
    //private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final CommentRepository commentRepository;
    private final CommentDtoMapper commentDtoMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserDtoMapper userDtoMapper,
                           CommentRepository commentRepository,
                           CommentDtoMapper commentDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
        this.commentRepository = commentRepository;
        this.commentDtoMapper =commentDtoMapper;
    }

    @Override
    public UserResDto createUser(UserReqDto userReq) {
        if(userRepository.existsUserByLogin(userReq.getLogin()))
            throw new BadDataException("user with such login is already exist");
        User savedUser = userRepository.save(userDtoMapper.userReqToUser(userReq));
        return userDtoMapper.userToUserRes(savedUser);
    }

    @Override
    public void deleteUser(String login) {
        if(!userRepository.existsUserByLogin(login))
            throw new NotFoundException("user not found");
        userRepository.delete(userRepository.getUserByLogin(login).get());
    }

    @Override
    public UserResDto getUser(String login) {
        if(!userRepository.existsUserByLogin(login))
            throw new NotFoundException("user not found");
        return userDtoMapper.userToUserRes(userRepository.getUserByLogin(login).get());
    }

    @Override
    public List<CommentResDto> getComments(Integer id) {
        if(!userRepository.existsUserById(id))
            throw new NotFoundException("user not found");
        return commentRepository.getCommentsByUserId(id)
                .stream()
                .map(comment -> commentDtoMapper.commentToCommentRes(comment))
                .toList();
    }
}
