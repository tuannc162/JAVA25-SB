package com.example.movie_app.controller;

import com.example.movie_app.entity.*;
import com.example.movie_app.model.enums.MovieType;
import com.example.movie_app.model.request.UpdatePasswordRequest;
import com.example.movie_app.model.request.UpdateProfileUserRequest;
import com.example.movie_app.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class WebController<HttpServletResquest> {
    private final MovieService movieService;
    private final BlogService blogService;
    private final EpisodeService episodeService;
    private final ReviewService reviewService;
    private final UserService userService;


    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Movie> listPhimBo = movieService.getMoviesByType(MovieType.PHIM_BO, true,1,6).getContent();
        List<Movie> listPhimLe = movieService.getMoviesByType(MovieType.PHIM_LE, true,1,6).getContent();
        List<Movie> listPhimChieuRap = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true,1,6).getContent();

        List<Movie> listPhimHot = movieService.getTopHotMovies(true,1, 4);
        model.addAttribute("topHotMovies", listPhimHot);

        Page<Blog> listBlog = blogService.getBlogsByStatus(true, 1, 4);
        model.addAttribute("listBlog", listBlog.getContent());

        model.addAttribute("listPhimBo",listPhimBo);
        model.addAttribute("listPhimLe",listPhimLe);
        model.addAttribute("listPhimChieuRap",listPhimChieuRap);
        return "web/index";
    }



    // /phim-bo?page=1&pageSize=12
    @GetMapping("/phim-bo")
    public String getPhimBoPage(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_BO, true,page,pageSize);
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage",page);
        return "web/phim-bo";
    }

    @GetMapping("/phim-le")
    public String getPhimLePage(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_LE, true,page,pageSize);
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage",page);
        return "web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true,page,pageSize);
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage",page);
        return "web/phim-chieu-rap";
    }






    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailPage(Model model,
                                    @PathVariable String slug,
                                    @PathVariable Integer id) {
        Movie movie = movieService.getMovieDetails(id, slug);

        List<Episode> episodes = episodeService.getEpisodesByMovieId(movie.getId());
        model.addAttribute("episodes", episodes);

        List<Review> reviews = reviewService.getReviewsByMovieIdSortedByCreatedAtDesc(movie.getId());
        model.addAttribute("reviews", reviews);

        List<Movie> recommendMovies = movieService.getMovieRecommended(movie.getType(),movie.getId(), true, 1, 6);

        model.addAttribute("recommendMovies", recommendMovies);
        model.addAttribute("movie", movie);
        return "web/chi-tiet-phim";
    }

    @GetMapping("/tin-tuc")
    public String getBlogPage(Model model,
                                      @RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "4") int pageSize) {
        Page<Blog> pageData = blogService.getBlogsByStatus(true, page, pageSize);

        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage",page);
        return "web/tin-tuc";
    }

    @GetMapping("/tin-tuc/{id}/{slug}")
    public String getBlogDetailPage(Model model,
                              @PathVariable String slug,
                              @PathVariable Integer id) {
        Blog blog = blogService.getBlogDetails(id, slug);
        model.addAttribute("blog", blog);
        return "web/chi-tiet-tin-tuc";
    }

    @GetMapping("/xem-phim/phim/{id}/{slug}")
    public String getMovielWatchPage(Model model,
                                     @PathVariable String slug,
                                     @PathVariable Integer id,
                                     @RequestParam String tap) {
        Movie movie = movieService.getMovieDetails(id, slug);

        List<Episode> episodes = episodeService.getEpisodesByMovieId(movie.getId());
        model.addAttribute("episodes", episodes);

        Episode currentEpisode = episodeService.getEpisodeByDisplayOrder(id, tap);
        model.addAttribute("currentEpisode", currentEpisode);

        List<Review> reviews = reviewService.getReviewsByMovieIdSortedByCreatedAtDesc(movie.getId());
        model.addAttribute("reviews", reviews);

        List<Movie> recommendMovies = movieService.getMovieRecommended(movie.getType(),movie.getId(), true, 1, 6);

        model.addAttribute("recommendMovies", recommendMovies);
        model.addAttribute("movie", movie);
        return "web/xem-phim";
    }

    @GetMapping("/dang-nhap")
    public String loginPage(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user != null) {
            return "redirect:/";
        }
        return "web/dang-nhap";
    }

    @GetMapping("/thong-tin-ca-nhan")
    public String informationPage(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user == null) {
            return "redirect:/dang-nhap";
        }
        model.addAttribute("user", user);
        return "web/thong-tin-ca-nhan";
    }

}
