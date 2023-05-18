package com.genius.tms_c61_genius.service;

import com.genius.tms_c61_genius.model.request.CommentReqDto;
import com.genius.tms_c61_genius.model.response.CommentResDto;

public interface CommentService {
    CommentResDto createComment(CommentReqDto commentReqDto);
    CommentResDto editComment(CommentReqDto commentReqDto,Integer id);
    void deleteCommentById(Integer commentId);

}
