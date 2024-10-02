package com.BookingHotel.BookingHotel.entity;

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
@Table(name = "amenities")
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer amenityId;

    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
