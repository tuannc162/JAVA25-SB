package com.example.movie_app.service;

import com.example.movie_app.entity.Review;
import com.example.movie_app.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> getReviewsByMovieIdSortedByCreatedAtDesc(Integer movieId) {
        return reviewRepository.findByMovieIdOrderByCreatedAtDesc(movieId);
    }
}
