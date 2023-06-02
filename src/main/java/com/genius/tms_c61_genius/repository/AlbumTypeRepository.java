package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.AlbumType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumTypeRepository extends JpaRepository<AlbumType,Integer> {
    Optional<AlbumType> findAlbumTypeByTypeName(String typeName);
    Optional<AlbumType> getAlbumTypeById(Integer id);
    void deleteById(Integer id);
    Boolean existsAlbumTypeById(Integer id);
    Boolean existsAlbumTypeByTypeName(String typeName);

}
