package com.BookingHotel.BookingHotel.repository;

import com.BookingHotel.BookingHotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
