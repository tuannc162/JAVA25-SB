package com.example.movie_app.controller;

import com.example.movie_app.repository.ActorRepository;
import com.example.movie_app.repository.CountryRepository;
import com.example.movie_app.repository.DirectorRepository;
import com.example.movie_app.repository.GenresRepository;
import com.example.movie_app.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/movies")
public class MovieController {
    private final MovieService movieService;
    private final CountryService countryService;
    private final GenreService genreService;
    private final ActorService actorService;
    private final DirectorService directorService;
    private final EpisodeService episodeService;


    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin/movie/index";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("actors", actorService.getAllActors());
        model.addAttribute("directors", directorService.getAllDirectors());
        return "admin/movie/create";
    }

    @GetMapping("/{id}/detail")
    public String getDetailPage(Model model ,@PathVariable Integer id) {
        model.addAttribute("movie", movieService.getMovieById(id));
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("actors", actorService.getAllActors());
        model.addAttribute("directors", directorService.getAllDirectors());

        // Lấy danh sách tập phim của phim và sắp xếp theo dislayOrder tăng dần
        model.addAttribute("episodes", episodeService.getEpisodesByMovieId(id));
        return "admin/movie/detail";
    }
}
