package com.example.movie_app.service;

import com.example.movie_app.entity.Episode;
import com.example.movie_app.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    public List<Episode> getEpisodesByMovieId(Integer movieId) {
        return episodeRepository.findByMovieIdOrderByDisplayOrderAsc(movieId);
    }
}
