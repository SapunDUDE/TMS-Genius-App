package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.AlbumType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumTypeRepository extends JpaRepository<AlbumType,Integer> {
    AlbumType findAlbumTypeByTypeName(String typeName);
    AlbumType getAlbumTypeById(Integer id);
    void deleteById(Integer id);

}
