package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.exception.BadDataException;
import com.genius.tms_c61_genius.exception.NotFoundException;
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
        if(genreRepository.existsGenreByGenreTitle(genreName))
            throw new BadDataException("genre with such title is already exist");
        return genreRepository.save(Genre.builder()
                .genreTitle(genreName)
                .build()).getGenreTitle();
    }

    @Override
    public String updateGenre(Integer genreId, String genreName) {
        if(genreRepository.existsGenreByGenreTitle(genreName))
            throw new BadDataException("genre with such title is already exist");
        if(!genreRepository.existsGenreById(genreId))
            throw new NotFoundException("genre not found");
        Genre updatedGenre = genreRepository.getGenreById(genreId);
        updatedGenre.setGenreTitle(genreName);
        return genreRepository.save(updatedGenre).getGenreTitle();
    }

    @Override
    public void deleteGenre(Integer genreId) {
        if(!genreRepository.existsGenreById(genreId))
            throw new NotFoundException("genre not found");
        genreRepository.deleteById(genreId);
    }

    @Override
    public String getGenre(Integer genreId) {
        if(!genreRepository.existsGenreById(genreId))
            throw new NotFoundException("genre not found");
        return genreRepository.getGenreById(genreId).getGenreTitle();
    }
}
