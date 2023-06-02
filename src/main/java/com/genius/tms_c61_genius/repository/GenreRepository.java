package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Integer> {
    Optional<Genre> findGenreByGenreTitle(String genreTitle);
    Optional<Genre> getGenreById(Integer id);
    void deleteById(Integer id);
    Boolean existsGenreById(Integer id);
    Boolean existsGenreByGenreTitle(String genreTitle);
}

