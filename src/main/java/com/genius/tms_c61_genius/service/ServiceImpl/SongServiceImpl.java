package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.exception.NotFoundException;
import com.genius.tms_c61_genius.mapper.CommentDtoMapper;
import com.genius.tms_c61_genius.model.response.CommentResDto;
import com.genius.tms_c61_genius.repository.CommentRepository;
import com.genius.tms_c61_genius.repository.SongRepository;
import com.genius.tms_c61_genius.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final CommentRepository commentRepository;
    private final CommentDtoMapper commentDtoMapper;
    @Autowired
    public SongServiceImpl(SongRepository songRepository,
                           CommentRepository commentRepository,
                           CommentDtoMapper commentDtoMapper) {
        this.songRepository = songRepository;
        this.commentRepository = commentRepository;
        this.commentDtoMapper = commentDtoMapper;
    }

    @Override
    public List<CommentResDto> getComments(Integer id) {
        if(!songRepository.existsSongById(id)){
            throw new NotFoundException("song not found");
        }
        return commentRepository.getCommentsBySongId(id)
                .stream()
                .map(comment -> commentDtoMapper.commentToCommentRes(comment))
                .toList();
    }
}
