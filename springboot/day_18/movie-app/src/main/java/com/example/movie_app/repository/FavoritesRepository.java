package com.example.movie_app.repository;

import com.example.movie_app.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorites,Integer> {
}
