package com.BookingHotel.BookingHotel.repository;

import com.BookingHotel.BookingHotel.entity.Blog;
import com.BookingHotel.BookingHotel.entity.Customer;
import com.BookingHotel.BookingHotel.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}
