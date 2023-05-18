package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.aspect.TrackExecutionTime;
import com.genius.tms_c61_genius.model.request.UserReqDto;
import com.genius.tms_c61_genius.model.response.CommentResDto;
import com.genius.tms_c61_genius.model.response.UserResDto;
import com.genius.tms_c61_genius.service.CommentService;
import com.genius.tms_c61_genius.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final CommentService commentService;
    @Autowired
    public UserController(UserService userService,CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }

    @PostMapping("/admin/user")
    @TrackExecutionTime
    public ResponseEntity<UserResDto> createUser(@Valid @RequestBody UserReqDto user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{login}")
    @TrackExecutionTime
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String login){
        userService.deleteUser(login);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{login}")
    public ResponseEntity<UserResDto> getUser(@PathVariable String login){
        return new ResponseEntity<>(userService.getUser(login),HttpStatus.OK);
    }

    @GetMapping("/user/comments")
    public ResponseEntity<List<CommentResDto>> getUserComments(@RequestParam Integer id){
        return new ResponseEntity<>(userService.getComments(id),HttpStatus.OK);
    }
}
