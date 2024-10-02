package com.BookingHotel.BookingHotel.repository;

import com.BookingHotel.BookingHotel.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
