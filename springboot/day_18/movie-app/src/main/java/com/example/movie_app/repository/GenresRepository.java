package com.example.movie_app.repository;

import com.example.movie_app.entity.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepository extends JpaRepository<Genres,Integer> {
}
