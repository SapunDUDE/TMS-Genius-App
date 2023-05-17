package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album,Integer> {

}
