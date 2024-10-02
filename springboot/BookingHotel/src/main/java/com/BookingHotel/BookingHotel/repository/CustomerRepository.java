package com.BookingHotel.BookingHotel.repository;

import com.BookingHotel.BookingHotel.entity.Customer;
import com.BookingHotel.BookingHotel.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByRole(UserRole role);

}
