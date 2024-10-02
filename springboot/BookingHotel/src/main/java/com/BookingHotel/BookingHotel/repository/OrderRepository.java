package com.BookingHotel.BookingHotel.repository;

import com.BookingHotel.BookingHotel.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
