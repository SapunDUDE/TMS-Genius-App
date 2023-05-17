package com.genius.tms_c61_genius.service;

import com.genius.tms_c61_genius.model.domain.User;
import com.genius.tms_c61_genius.model.request.UserReqDto;
import com.genius.tms_c61_genius.model.response.UserResDto;

public interface UserService {
    UserResDto createUser(UserReqDto user);

    void deleteUser(String login);

    UserResDto getUser(String login);

}
