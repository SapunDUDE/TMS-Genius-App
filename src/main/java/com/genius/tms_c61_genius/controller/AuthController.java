package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.model.request.AuthReqDto;
import com.genius.tms_c61_genius.model.response.AuthResDto;
import com.genius.tms_c61_genius.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping()
    public ResponseEntity<AuthResDto> auth(@RequestBody AuthReqDto authReqDto){
        String token = authService.getTokenFromAuthRequest(authReqDto);
        return new ResponseEntity<>(new AuthResDto(token), HttpStatus.CREATED);
    }
}
