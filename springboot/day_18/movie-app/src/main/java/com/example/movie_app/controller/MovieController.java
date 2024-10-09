package com.example.movie_app.controller;

import com.example.movie_app.repository.ActorRepository;
import com.example.movie_app.repository.CountryRepository;
import com.example.movie_app.repository.DirectorRepository;
import com.example.movie_app.repository.GenresRepository;
import com.example.movie_app.service.MovieService;
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
    private final CountryRepository countryRepository;
    private final GenresRepository genreRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;


    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin/movie/index";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("directors", directorRepository.findAll());
        return "admin/movie/create";
    }

    @GetMapping("/{id}/detail")
    public String getDetailPage(Model model ,@PathVariable Integer id) {
        model.addAttribute("movie", movieService.getMovieById(id));
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("directors", directorRepository.findAll());
        return "admin/movie/detail";
    }
}
