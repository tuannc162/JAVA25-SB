package com.example.movie_app.rest;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.request.CreateMovieRequest;
import com.example.movie_app.model.request.UpsertMovieRequest;
import com.example.movie_app.model.response.ErrorResponse;
import com.example.movie_app.model.response.FileResponse;
import com.example.movie_app.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class MovieApi {
    private final MovieService movieService;

    @PutMapping("/{id}")
    public ResponseEntity<?> upserMovie(@PathVariable Integer id,
                                        @RequestBody UpsertMovieRequest request) {
        Movie movie = movieService.upsertMovie(id, request);
        return ResponseEntity.ok(movie);
    }


    @PostMapping("/{id}/upload-poster")
    public ResponseEntity<?> uploadPoster(@PathVariable Integer id,
                                          @RequestParam MultipartFile file) {
        String path = movieService.uploadPoster(id, file);
        FileResponse fileResponse = FileResponse.builder()
                .url(path)
                .build();
        return ResponseEntity.ok(fileResponse);
    }


    @PostMapping()
    public ResponseEntity<?> createMovie(@RequestBody CreateMovieRequest request) {
        try {
            Movie movie = movieService.createMovie(request);
            return ResponseEntity.ok(movie);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
