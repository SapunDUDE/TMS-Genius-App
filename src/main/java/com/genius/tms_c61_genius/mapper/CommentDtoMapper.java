package com.genius.tms_c61_genius.mapper;

import com.genius.tms_c61_genius.model.domain.Comment;
import com.genius.tms_c61_genius.model.request.CommentReqDto;
import com.genius.tms_c61_genius.model.response.CommentResDto;
import com.genius.tms_c61_genius.repository.SongRepository;
import com.genius.tms_c61_genius.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoMapper {
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    @Autowired
    public CommentDtoMapper(UserRepository userRepository,
                            SongRepository songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public Comment commentReqToComment(CommentReqDto commentReqDto){
        return Comment.builder()
                .content(commentReqDto.getContent())
                .rating(commentReqDto.getRating())
                .user(userRepository.getUserById(commentReqDto.getUserId()).get())
                .song(songRepository.getSongsById(commentReqDto.getSongId()).get())
                .build();
    }

    public CommentResDto commentToCommentRes(Comment comment){
        return CommentResDto.builder()
                .content(comment.getContent())
                .rating(comment.getRating())
                .songTitle(comment.getSong().getSongTitle())
                .userName(comment.getUser().getNickName())
                .build();
    }

    public Comment editComment(Comment oldComment,CommentReqDto commentReqDto){
        if(commentReqDto.getContent()!=null)
            oldComment.setContent(commentReqDto.getContent());

        if(commentReqDto.getRating()!=0)
            oldComment.setRating(commentReqDto.getRating());

        return oldComment;
    }
}
