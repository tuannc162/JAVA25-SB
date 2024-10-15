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

    public Episode getEpisodeByDisplayOrder(Integer movieId, String tap) {
        Integer covertTap = tap.equals("full") ? 1 : Integer.parseInt(tap);
        return episodeRepository
                .findByMovie_IdAndStatusAndDisplayOrder(movieId, true, covertTap)
                .orElse(null);
    }


//    public List<Episode> getEpisodeListOfMovieByAdmin(Integer movieId) {
//        return episodeRepository.findByMovie_IdOrderByDisplayOrderAsc(movieId);
//    }
}
