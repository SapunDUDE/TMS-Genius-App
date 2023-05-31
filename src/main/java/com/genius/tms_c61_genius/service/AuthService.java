package com.genius.tms_c61_genius.service;

import com.genius.tms_c61_genius.model.request.AuthReqDto;

public interface AuthService {
    String getTokenFromAuthRequest(AuthReqDto authReqDto);

}
