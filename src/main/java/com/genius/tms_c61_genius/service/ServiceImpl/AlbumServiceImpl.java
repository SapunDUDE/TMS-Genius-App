package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.mapper.AlbumDtoMapper;
import com.genius.tms_c61_genius.model.request.AlbumReqDto;
import com.genius.tms_c61_genius.model.response.AlbumResDto;
import com.genius.tms_c61_genius.repository.AlbumRepository;
import com.genius.tms_c61_genius.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumDtoMapper albumDtoMapper;
    private final AlbumRepository albumRepository;
    @Autowired
    public AlbumServiceImpl(AlbumDtoMapper albumDtoMapper, AlbumRepository albumRepository) {
        this.albumDtoMapper = albumDtoMapper;
        this.albumRepository = albumRepository;
    }

    @Override
    public AlbumResDto createAlbum(AlbumReqDto albumReqDto) {
        return null;
    }
}
