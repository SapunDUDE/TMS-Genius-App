package com.genius.tms_c61_genius.service;

public interface GenreService {
    String createGenre(String genreName);
    String updateGenre(Integer genreId,String genreName);
    void deleteGenre(Integer genreId);
    String getGenre(Integer genreId);
}
