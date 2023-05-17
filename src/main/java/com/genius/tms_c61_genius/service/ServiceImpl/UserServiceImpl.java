package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.mapper.UserDtoMapper;
import com.genius.tms_c61_genius.model.domain.User;
import com.genius.tms_c61_genius.model.request.UserReqDto;
import com.genius.tms_c61_genius.model.response.UserResDto;
import com.genius.tms_c61_genius.repository.UserRepository;
import com.genius.tms_c61_genius.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    //TODO add password encoder and custom exceptions
    //private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public UserResDto createUser(UserReqDto userReq) {
        User savedUser = userRepository.save(userDtoMapper.userReqToUser(userReq));
        return userDtoMapper.userToUserRes(savedUser);
    }

    @Override
    public void deleteUser(String login) {
         userRepository.delete(
                userRepository.getUserByLogin(login).get()
        );
    }

    @Override
    public UserResDto getUser(String login) {
        return userDtoMapper.userToUserRes(userRepository.getUserByLogin(login).get());
    }
}
