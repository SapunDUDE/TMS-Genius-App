package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.model.request.UserReqDto;
import com.genius.tms_c61_genius.model.response.UserResDto;
import com.genius.tms_c61_genius.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin/user")
    public ResponseEntity<UserResDto> createUser(@RequestBody UserReqDto user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{login}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String login){
        userService.deleteUser(login);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{login}")
    public ResponseEntity<UserResDto> getUser(@PathVariable String login){
        return new ResponseEntity<>(userService.getUser(login),HttpStatus.OK);
    }
}
