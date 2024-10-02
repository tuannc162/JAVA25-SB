package com.BookingHotel.BookingHotel.repository;

import com.BookingHotel.BookingHotel.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
