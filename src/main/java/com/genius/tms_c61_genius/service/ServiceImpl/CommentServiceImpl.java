package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.exception.BadDataException;
import com.genius.tms_c61_genius.exception.NotFoundException;
import com.genius.tms_c61_genius.mapper.CommentDtoMapper;
import com.genius.tms_c61_genius.model.request.CommentReqDto;
import com.genius.tms_c61_genius.model.response.CommentResDto;
import com.genius.tms_c61_genius.repository.CommentRepository;
import com.genius.tms_c61_genius.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.genius.tms_c61_genius.model.domain.Comment;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDtoMapper commentDtoMapper;
    private final CommentRepository commentRepository;
    @Autowired
    public CommentServiceImpl(CommentDtoMapper commentDtoMapper,
                              CommentRepository commentRepository) {
        this.commentDtoMapper = commentDtoMapper;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public CommentResDto createComment(CommentReqDto commentReqDto) {
        Comment newComment = commentDtoMapper.commentReqToComment(commentReqDto);
        commentRepository.save(newComment);
        return commentDtoMapper.commentToCommentRes(newComment);
    }

    @Override
    public CommentResDto editComment(CommentReqDto commentReqDto, Integer id) {
        if(!commentRepository.existsCommentById(id))
            throw new NotFoundException("comment not found");
        Comment oldComment = commentRepository.getCommentById(id).get();
        oldComment = commentRepository.save(commentDtoMapper.editComment(oldComment,commentReqDto));
        return commentDtoMapper.commentToCommentRes(oldComment);
    }

    @Override
    @Transactional
    public void deleteCommentById(Integer commentId) {
        if(!commentRepository.existsCommentById(commentId))
            throw new NotFoundException("comment not found");
        commentRepository.delete(commentRepository.getCommentById(commentId).get());
    }
}
