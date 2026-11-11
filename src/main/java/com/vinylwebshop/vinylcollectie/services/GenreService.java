package com.vinylwebshop.vinylcollectie.services;

import com.vinylwebshop.vinylcollectie.entities.Genre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final List<Genre> genres = new ArrayList<>();
    private long nextId = 1L;

    public GenreService() {
        createGenre(new Genre(null, "Rock"));
        createGenre(new Genre(null, "Jazz"));
        createGenre(new Genre(null, "Metal"));
    }

        public List<Genre> findAllGenres() {
            return new ArrayList<>(genres);
        }

    public Optional<Genre> findGenreById(Long id) {
        return genres.stream()
                .filter(genre -> genre.getId().equals(id))
                .findFirst();
    }

    public Genre createGenre(Genre genre) {
        genre.setId(nextId);
        nextId++;

        genres.add(genre);

        return genre;
    }

    public Optional<Genre> updateGenre(Long id, Genre updatedGenre) {
        Optional<Genre> existingGenre = findGenreById(id);

        if (existingGenre.isEmpty()) {
            return Optional.empty();
        }

        Genre genre = existingGenre.get();
        genre.setName(updatedGenre.getName());

        return Optional.of(genre);
    }

    public boolean deleteGenre(Long id) {
        return genres.removeIf(genre -> genre.getId().equals(id));
    }
}
