package com.example.movie_app.service;

import com.example.movie_app.entity.Genres;
import com.example.movie_app.repository.GenresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenresRepository genreRepository;

    public List<Genres> getAllGenres() {
        return genreRepository.findAll();
    }
}
