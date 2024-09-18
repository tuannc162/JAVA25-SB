package com.example.movie_app.service;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.entity.Review;
import com.example.movie_app.entity.User;
import com.example.movie_app.model.request.CreateReviewRequest;
import com.example.movie_app.model.request.UpdateReviewRequest;
import com.example.movie_app.repository.MovieRepository;
import com.example.movie_app.repository.ReviewRepository;
import com.example.movie_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public List<Review> getReviewsByMovieIdSortedByCreatedAtDesc(Integer movieId) {
        return reviewRepository.findByMovieIdOrderByCreatedAtDesc(movieId);
    }

    public Review createReview(CreateReviewRequest request) {
    // TODO: Fix user. Sau này user sẽ là user đăng nhập
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + request.getMovieId()));


        //TODO: Bổ sung validation rating, content
        Review review = Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .movie(movie)
                .user(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return reviewRepository.save(review);
    }

    public Review updateReview(Integer id, UpdateReviewRequest request) {
        // TODO: Fix user. Sau này user sẽ là user đăng nhập
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

        // Check user is owner of review
        if (!review.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not owner of this review");
        }

        //TODO: Bổ sung validation rating, content
        review.setContent(request.getContent());
        review.setRating(request.getRating());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public Review deleteReview(Integer id) {
        // TODO: Fix user. Sau này user sẽ là user đăng nhập
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

        // Check user is owner of review
        if (!review.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not owner of this review");
        }
        return reviewRepository.save(review);
    }
}
