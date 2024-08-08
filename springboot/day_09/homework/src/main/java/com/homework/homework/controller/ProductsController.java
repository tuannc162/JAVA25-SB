package com.homework.homework.controller;

import com.homework.homework.model.Products;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @GetMapping
    public List<Products> getProducts() {
        return Arrays.asList(
                new Products("1", "iPhone 14 Pro", "Verry Good!", 999, "Apple"),
                new Products("2", "iPhone 14 Pro Max", "Verry Good!", 799, "Apple"),
                new Products("3", "iPhone 15 Pro", "Verry Good!", 699, "Apple"),
                new Products("4", "iPhone 15 Pro max", "Verry Good!", 729, "Apple"),
                new Products("5", "Samsung Galaxy S21", "Verry Good!", 649, "Samsung"),
                new Products("6", "Samsung Galaxy S22", "Verry Good!", 899, "Samsung"),
                new Products("7", "Samsung Galaxy S23", "Verry Good!", 499, "Samsung"),
                new Products("8", "Xiaomi Note 15", "Verry Good!", 799, "Xiaomi"),
                new Products("9", "Xiaomi Note 16", "Verry Good!", 599, "Xiaomi"),
                new Products("10", "Oppo S20", "Verry Good!", 249, "Oppo")
        );
    }
}
