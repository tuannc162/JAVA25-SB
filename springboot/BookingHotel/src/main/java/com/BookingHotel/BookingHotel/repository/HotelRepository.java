package com.BookingHotel.BookingHotel.repository;

import com.BookingHotel.BookingHotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
