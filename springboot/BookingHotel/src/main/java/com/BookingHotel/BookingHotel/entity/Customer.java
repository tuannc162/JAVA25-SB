package com.BookingHotel.BookingHotel.entity;

import com.BookingHotel.BookingHotel.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer customerId;

    @Column(unique = true, nullable = false)
    String name;

    @Column(unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    String phone;
    String address;
    String avatar;

    @Enumerated(EnumType.STRING)
    UserRole role;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
