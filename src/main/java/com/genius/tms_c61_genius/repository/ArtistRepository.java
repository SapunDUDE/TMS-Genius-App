package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.Album;
import com.genius.tms_c61_genius.model.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Integer> {
    Optional<Artist> getArtistByPersonInfoNickname(String nickname);
    Artist getArtistByUser_Login(String userLogin);
    void deleteArtistByUser_Login(String userLogin);

    void deleteArtistByPersonInfoNickname(String nickname);
    List<Artist> getArtistByAlbums(Album album);
}
