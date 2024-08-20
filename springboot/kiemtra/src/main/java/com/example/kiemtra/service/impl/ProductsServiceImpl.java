package com.example.kiemtra.service.impl;

import com.example.kiemtra.dao.ProductsDAO;
import com.example.kiemtra.model.Products;
import com.example.kiemtra.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    private ProductsDAO productsDAO ;

    @Override
    public List<Products> getAllProducts() {
        return productsDAO.getProducts();
    }

    public List<Products> findProductsByTitle(String keyword) {
        return productsDAO.findByTitleContainsIgnoreCase(keyword);
    }
}
