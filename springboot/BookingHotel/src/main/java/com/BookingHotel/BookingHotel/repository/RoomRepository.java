package com.BookingHotel.BookingHotel.repository;

import com.BookingHotel.BookingHotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
