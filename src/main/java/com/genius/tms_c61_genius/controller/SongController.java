package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.aspect.TrackExecutionTime;
import com.genius.tms_c61_genius.model.request.CommentReqDto;
import com.genius.tms_c61_genius.model.response.CommentResDto;
import com.genius.tms_c61_genius.service.CommentService;
import com.genius.tms_c61_genius.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {
    private final CommentService commentService;
    private final SongService songService;

    @Autowired
    public SongController(CommentService commentService,SongService songService) {
        this.commentService = commentService;
        this.songService = songService;
    }
    @PostMapping("/comment/add")
    @TrackExecutionTime
    ResponseEntity<CommentResDto>createComment(@RequestBody CommentReqDto commentReqDto){
        return new ResponseEntity<>(commentService.createComment(commentReqDto), HttpStatus.CREATED);
    }
    @PutMapping("/comment/edit")
    @TrackExecutionTime
    ResponseEntity<CommentResDto>editComment(@RequestBody CommentReqDto commentReqDto, @RequestParam Integer id){
        return new ResponseEntity<>(commentService.editComment(commentReqDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/comment/delete")
    @TrackExecutionTime
    ResponseEntity<HttpStatus>deleteComment(@RequestParam Integer id){
        commentService.deleteCommentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/comments")
    @TrackExecutionTime
    ResponseEntity<List<CommentResDto>>getSongComments(@RequestParam Integer id){
        return new ResponseEntity<>(songService.getComments(id),HttpStatus.OK);
    }
}
