package com.example.kiemtra.dao;

import com.example.kiemtra.model.Products;

import java.util.List;

public interface ProductsDAO {
    List<Products> getProducts();
    Products findById(int id);
    List<Products> findByTitleContainsIgnoreCase(String keyword);
}
