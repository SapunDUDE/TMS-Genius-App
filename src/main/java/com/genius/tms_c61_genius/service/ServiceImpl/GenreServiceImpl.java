package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.model.domain.Genre;
import com.genius.tms_c61_genius.repository.GenreRepository;
import com.genius.tms_c61_genius.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public String createGenre(String genreName) {
        return genreRepository.save(Genre.builder()
                .genreTitle(genreName)
                .build()).getGenreTitle();
    }

    @Override
    public String updateGenre(Integer genreId, String genreName) {
        Genre updatedGenre = genreRepository.getGenreById(genreId);
        updatedGenre.setGenreTitle(genreName);
        return genreRepository.save(updatedGenre).getGenreTitle();
    }

    @Override
    public void deleteGenre(Integer genreId) {
        genreRepository.deleteById(genreId);
    }

    @Override
    public String getGenre(Integer genreId) {
        return genreRepository.getGenreById(genreId).getGenreTitle();
    }
}
