package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.Artist;
import com.genius.tms_c61_genius.model.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,Integer> {
    List<Song>deleteSongsByAlbumAlbumTitle(String albumTitle);

    List<Song>getSongsByAlbum_AlbumTitle(String albumTitle);

    List<Song>getSongsByArtists(Artist artist);
}
