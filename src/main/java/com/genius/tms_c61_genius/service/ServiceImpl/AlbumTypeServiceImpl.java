package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.exception.BadDataException;
import com.genius.tms_c61_genius.exception.NotFoundException;
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
        if(albumTypeRepository.existsAlbumTypeByTypeName(albumTypeName))
            throw new BadDataException("album type with such name is already exist");
        return albumTypeRepository.save(AlbumType.builder()
                .typeName(albumTypeName)
                .build()).getTypeName();
    }

    @Override
    public String updateAlbumType(Integer albumTypeId, String albumTypeName) {
        if(!albumTypeRepository.existsAlbumTypeById(albumTypeId))
            throw new NotFoundException("album type not found");
        if(albumTypeRepository.existsAlbumTypeByTypeName(albumTypeName))
            throw new BadDataException("album with such name is already exist");
        AlbumType updatedAlbumType = albumTypeRepository.getAlbumTypeById(albumTypeId);
        updatedAlbumType.setTypeName(albumTypeName);
        return albumTypeRepository.save(updatedAlbumType).getTypeName();
    }

    @Override
    public void deleteAlbumType(Integer albumTypeId) {
        if(!albumTypeRepository.existsAlbumTypeById(albumTypeId))
            throw new NotFoundException("album type not found");
        albumTypeRepository.deleteById(albumTypeId);
    }

    @Override
    public String getAlbumType(Integer albumTypeId) {
        if(!albumTypeRepository.existsAlbumTypeById(albumTypeId))
            throw new NotFoundException("album type not found");
        return albumTypeRepository.getAlbumTypeById(albumTypeId).getTypeName();
    }
}
