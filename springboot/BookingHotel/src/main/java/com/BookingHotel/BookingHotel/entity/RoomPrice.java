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
@Table(name = "room_prices")
public class RoomPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer priceId;

    Integer basePrice;
    Integer additionalPrice;
    LocalDateTime effectiveDate;
    LocalDateTime endDate;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "room_id")
    Room room;

}
