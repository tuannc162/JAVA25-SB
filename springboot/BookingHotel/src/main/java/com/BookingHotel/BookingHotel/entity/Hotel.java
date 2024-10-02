package com.BookingHotel.BookingHotel.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer hotelId;

    String name;
    String address;
    String phone;
    String email;
    Integer rating;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(columnDefinition = "TEXT")
    String policy;

    String mainImage;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    List<Integer> services = new ArrayList<>();

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    List<String> subImages = new ArrayList<>();


    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "region_id")
    Region region;
}
