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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @TrackExecutionTime
    public ResponseEntity<UserResDto> createUser(@Valid @RequestBody UserReqDto user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{login}")
    @TrackExecutionTime
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{login}")
    @TrackExecutionTime
    public ResponseEntity<UserResDto> getUser(@PathVariable String login) {
        return new ResponseEntity<>(userService.getUser(login), HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentResDto>> getUserComments(@RequestParam Integer id) {
        return new ResponseEntity<>(userService.getComments(id), HttpStatus.OK);
    }
}
