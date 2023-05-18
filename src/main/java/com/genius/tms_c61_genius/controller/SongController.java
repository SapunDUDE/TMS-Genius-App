package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.model.request.CommentReqDto;
import com.genius.tms_c61_genius.model.response.CommentResDto;
import com.genius.tms_c61_genius.service.CommentService;
import com.genius.tms_c61_genius.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongController {
    private final CommentService commentService;
    private final SongService songService;
    @Autowired
    public SongController(CommentService commentService,SongService songService) {
        this.commentService = commentService;
        this.songService = songService;
    }
    @PostMapping("/song/comment/add")
    ResponseEntity<CommentResDto>createComment(@RequestBody CommentReqDto commentReqDto){
        return new ResponseEntity<>(commentService.createComment(commentReqDto), HttpStatus.CREATED);
    }
    @PutMapping("/song/comment/edit")
    ResponseEntity<CommentResDto>editComment(@RequestBody CommentReqDto commentReqDto, @RequestParam Integer id){
        return new ResponseEntity<>(commentService.editComment(commentReqDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/song/comment/delete")
    ResponseEntity<HttpStatus>deleteComment(@RequestParam Integer id){
        commentService.deleteCommentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/song/comments")
    ResponseEntity<List<CommentResDto>>getSongComments(@RequestParam Integer id){
        return new ResponseEntity<>(songService.getComments(id),HttpStatus.OK);
    }
}
