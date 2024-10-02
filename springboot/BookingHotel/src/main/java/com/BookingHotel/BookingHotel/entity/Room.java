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
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer roomId;

    String roomNumber;
    Boolean availability;
    String type;

    @Column(columnDefinition = "TEXT")
    String description;

    String mainImage;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    List<String> subImage;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    List<Integer> amenity;

    Integer numberOfGuests;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "roomType_id")
    RoomType roomType;
}
