package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.model.domain.AlbumType;
import com.genius.tms_c61_genius.repository.AlbumTypeRepository;
import com.genius.tms_c61_genius.service.AlbumTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumTypeServiceImpl implements AlbumTypeService {
    private final AlbumTypeRepository albumTypeRepository;
    @Autowired
    public AlbumTypeServiceImpl(AlbumTypeRepository albumTypeRepository) {
        this.albumTypeRepository = albumTypeRepository;
    }

    @Override
    public String createAlbumType(String albumTypeName) {
        return albumTypeRepository.save(AlbumType.builder()
                .typeName(albumTypeName)
                .build()).getTypeName();
    }

    @Override
    public String updateAlbumType(Integer albumTypeId, String albumTypeName) {
        AlbumType updatedAlbumType = albumTypeRepository.getAlbumTypeById(albumTypeId);
        updatedAlbumType.setTypeName(albumTypeName);
        return albumTypeRepository.save(updatedAlbumType).getTypeName();
    }

    @Override
    public void deleteAlbumType(Integer albumTypeId) {
        albumTypeRepository.deleteById(albumTypeId);
    }

    @Override
    public String getAlbumType(Integer albumTypeId) {
        return albumTypeRepository.getAlbumTypeById(albumTypeId).getTypeName();
    }
}
