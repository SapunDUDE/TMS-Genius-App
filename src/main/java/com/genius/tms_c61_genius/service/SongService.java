package com.genius.tms_c61_genius.service;


import com.genius.tms_c61_genius.model.response.CommentResDto;

import java.util.List;

public interface SongService {
    List<CommentResDto> getComments(Integer id);
}
