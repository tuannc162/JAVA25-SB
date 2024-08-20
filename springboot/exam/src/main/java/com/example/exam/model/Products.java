package com.example.exam.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Products {
    int id;
    String name;
    String description;
    int price;
    double rating;
    int priceDiscount;
}
