package com.example.movie_app.repository;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MovieRepository extends JpaRepository<Movie,Integer> {
    List<Movie> findByName(String name);

    List<Movie> findByNameContainingIgnoreCase(String name);

    Movie findByNameAndSlug(String name, String slug);

    List<Movie> findByRatingBetween(Double min, Double max);

    List<Movie> findByReleaseYearGreaterThanEqual(Integer year);

    List<Movie> findByStatusOrderByReleaseYearDesc(Boolean status);

    long countByType(MovieType type);

    boolean existsByRatingGreaterThan(Double rating);

    List<Movie> findByStatus(Boolean status, Sort sort);

    Page<Movie> findByStatus(Boolean status, Pageable pageable);

    // Tìm kiếm các bộ phim theo loại và status sắp xếp theo thời gian tạo giảm dần, rating tăng dần và lấy 5 bản ghi đầu tiên
    List<Movie> findTop5ByTypeAndStatusOrderByCreatedAtDescRatingAsc(MovieType type, Boolean status);

    // Ứng dụng Movie
    Page<Movie> findByTypeAndStatus(MovieType type, Boolean status, Pageable pageable);


    List<Movie> findTop4ByStatusOrderByRatingDesc(Boolean status, Pageable pageable);

    Optional<Movie> findByIdAndSlugAndStatus(Integer id, String slug, Boolean status);

    // Phim đề xuất:
    // Những bộ phim cùng loại (phim lẻ, phim chiếu rạp,...) -> type
    // Không chứa phim đang xem chi tiết -> id
    // Là các phim có status = true, rating giảm dần -> status, rating
    // Lấy 6 bản ghi -> top6


    // C1: Viết method trực tiếp trong Repo để lấy giá trị
    Page<Movie> findTop6ByTypeAndIdNotAndStatusOrderByRatingDesc(MovieType type, Integer id, Boolean status, Pageable pageable);



    // C2: Lấy danh sách -> sử dụng stream API để lọc theo yêu cầu. Lấy danh sách (tất cả phim, danh sách phim bộ, danh sách phim có status = true)






}
