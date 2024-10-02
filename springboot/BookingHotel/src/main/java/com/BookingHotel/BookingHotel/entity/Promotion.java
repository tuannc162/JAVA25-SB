package com.BookingHotel.BookingHotel.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer promotionId;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    List<Integer> roomList;

    String promotionName;

    @Column(columnDefinition = "TEXT")
    String description;

    Integer discountRate;
    LocalDateTime startDate;
    LocalDateTime endDate;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    Hotel hotel;
}