package com.genius.tms_c61_genius.service;

import com.genius.tms_c61_genius.model.request.AlbumReqDto;
import com.genius.tms_c61_genius.model.response.AlbumResDto;
import com.genius.tms_c61_genius.model.response.SongResDto;

import java.util.List;

public interface AlbumService {
    AlbumResDto createAlbum(AlbumReqDto albumReqDto);
    void  deleteAlbum(String albumTitle);
    AlbumResDto getAlbumByTitle(String albumTitle);
    List<SongResDto> getSongs(String albumTitle);
    List<String> getProducers(String albumTitle);
    List<String> getArtists(String albumTitle);
    List<String> getUsers(List<String>artistNickNames);
}
