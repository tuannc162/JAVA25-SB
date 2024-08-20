package com.example.kiemtra.service;

import com.example.kiemtra.model.Products;

import java.util.List;

public interface ProductsService {
    List<Products> getAllProducts();
    List<Products> findProductsByTitle(String keyword);
}
