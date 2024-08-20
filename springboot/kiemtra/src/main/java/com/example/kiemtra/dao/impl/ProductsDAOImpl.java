package com.example.kiemtra.dao.impl;

import com.example.kiemtra.dao.ProductsDAO;
import com.example.kiemtra.database.ProductsDB;
import com.example.kiemtra.model.Products;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductsDAOImpl implements ProductsDAO {
    @Override
    public List<Products> getProducts() {

        return ProductsDB.products;
    }

    @Override
    public Products findById(int id) {
        for (Products product : ProductsDB.products) {
            if(product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Products> findByTitleContainsIgnoreCase(String keyword) {
        List<Products> result = new ArrayList<>();
        for (Products product : ProductsDB.products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

}
