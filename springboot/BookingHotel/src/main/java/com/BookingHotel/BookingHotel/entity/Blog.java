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
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer blogId;

    @Column(unique = true, nullable = false)
    String title;
    String slug;

    @Column(columnDefinition = "TEXT", nullable = false)
    String description;

    @Column(columnDefinition = "TEXT")
    String content;

    String thumbnail;
    boolean status;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime publishedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;
}