package com.genius.tms_c61_genius.service;

public interface AlbumTypeService {
    String createAlbumType(String albumTypeName);
    String updateAlbumType(Integer albumTypeId,String albumTypeName);
    void deleteAlbumType(Integer albumTypeId);
    String getAlbumType(Integer albumTypeId);
}
