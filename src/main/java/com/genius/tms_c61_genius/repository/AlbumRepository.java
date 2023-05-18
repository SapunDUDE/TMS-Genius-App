package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.Album;
import com.genius.tms_c61_genius.model.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album,Integer> {
    Album getAlbumByAlbumTitle(String albumTitle);
    List<Album> findAlbumsByArtists(Artist artist);
    Boolean existsAlbumByAlbumTitle(String albumTitle);

}
