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
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer serviceId;

    String serviceName;

    @Column(columnDefinition = "TEXT")
    String description;

    Integer price;
    Boolean isFree;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}