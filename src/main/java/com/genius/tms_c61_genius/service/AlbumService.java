package com.genius.tms_c61_genius.service;

import com.genius.tms_c61_genius.model.domain.Album;
import com.genius.tms_c61_genius.model.request.AlbumReqDto;
import com.genius.tms_c61_genius.model.response.AlbumResDto;

public interface AlbumService {
    AlbumResDto createAlbum(AlbumReqDto albumReqDto);
}
