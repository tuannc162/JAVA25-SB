package com.example.movie_app.service;

import com.example.movie_app.entity.*;
import com.example.movie_app.model.enums.MovieType;
import com.example.movie_app.model.request.CreateMovieRequest;
import com.example.movie_app.model.request.UpsertMovieRequest;
import com.example.movie_app.repository.*;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CountryRepository countryRepository;
    private final GenresRepository genreRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final CloudinaryService cloudinaryService;


    public Page<Movie> getMoviesByType(MovieType type, Boolean status, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending().and(Sort.by("rating").ascending()));

        return movieRepository.findByTypeAndStatus(type, status, pageable);
    }

    public List<Movie> getTopHotMovies(Boolean status, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("rating").descending());
        return movieRepository.findTop4ByStatusOrderByRatingDesc(status, pageable);
    }

    public Movie getMovieDetails (Integer id, String slug) {
        return movieRepository.findByIdAndSlugAndStatus(id, slug, true).orElse(null);
    }

    public List<Movie> getMovieRecommended (MovieType type, Integer id, Boolean status, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("rating").descending());
        return movieRepository.findTop6ByTypeAndIdNotAndStatusOrderByRatingDesc(type, id, status, pageable).getContent();
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll(Sort.by("createdAt").descending());
    }

    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie upsertMovie(Integer id, UpsertMovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        List<Genres> genres = genreRepository.findAllById(request.getGenreIds());
        List<Actor> actors = actorRepository.findAllById(request.getActorIds());
        List<Director> directors = directorRepository.findAllById(request.getDirectorIds());

        Slugify slugify = Slugify.builder().build();
        movie.setName(request.getName());
        movie.setSlug(slugify.slugify(request.getName()));
        movie.setTrailerUrl(request.getTrailerUrl());
        movie.setDescription(request.getDescription());
        movie.setGenres(genres);
        movie.setActors(actors);
        movie.setDirectors(directors);
        movie.setStatus(request.getStatus());
        movie.setType(request.getType());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setCountry(country);
        movie.setUpdatedAt(LocalDateTime.now());
        movie.setPublishedAt(request.getStatus() ? LocalDateTime.now() : null);
        return movieRepository.save(movie);
    }

    public String uploadPoster(Integer id, MultipartFile file) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        try {
            Map map = cloudinaryService.uploadFile(file, "java-25-movie");
            String path = map.get("url").toString();
            movie.setPoster(path);
            movieRepository.save(movie);
            return path;

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file");
        }
    }

    public Movie createMovie(CreateMovieRequest request) {
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        List<Genres> genres = genreRepository.findAllById(request.getGenreIds());
        List<Director> directors = directorRepository.findAllById(request.getDirectorIds());
        List<Actor> actors = actorRepository.findAllById(request.getActorIds());

        Slugify slugify = Slugify.builder().build();
        Movie movie = Movie.builder()
                .name(request.getName())
                .slug(slugify.slugify(request.getName()))
                .poster("https://placehold.co/600x400?text=" + request.getName().substring(0,1).toUpperCase())
                .trailerUrl(request.getTrailerUrl())
                .description(request.getDescription())
                .genres(genres)
                .actors(actors)
                .directors(directors)
                .status(request.getStatus())
                .type(request.getType())
                .releaseYear(request.getReleaseYear())
                .country(country)
                .createdAt(LocalDateTime.now())
                .publishedAt(request.getStatus() ? LocalDateTime.now() : null)
                .build();

        return movieRepository.save(movie);
    }

}
