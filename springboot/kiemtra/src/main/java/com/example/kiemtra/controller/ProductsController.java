package com.example.kiemtra.controller;

import com.example.kiemtra.model.PageResponse;
import com.example.kiemtra.model.PageResponseImpl;
import com.example.kiemtra.model.Products;
import com.example.kiemtra.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.kiemtra.database.ProductsDB.products;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    // danh sách sản phẩm
    @GetMapping
    public String getProducts(Model model,
                              @RequestParam(required = false) String keyword,
                              @RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false, defaultValue = "10") int size) {

        List<Products> products;
        PageResponse<Products> pageResponse;

        if (keyword != null && !keyword.trim().isEmpty()) {
            products = productsService.findProductsByTitle(keyword);
        } else {
            products = productsService.getAllProducts();
        }

        pageResponse = PageResponseImpl.<Products>builder()
                .currentPage(page)
                .pageSize(size)
                .data(products)
                .build();

        model.addAttribute("pageResponse", pageResponse);
        //model.addAttribute("products", pageResponse.getContent());
        return "index";
    }

    @GetMapping("/{id}")
    public String getProductDetails(Model model, @PathVariable int id) {
        Products product = productsService.getAllProducts().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("product", product);

        // Tìm kiếm sản phẩm liên quan
        List<Products> relatedProducts = productsService.getAllProducts().stream()
                .filter(p -> p.getId() != id)
                .sorted((p1, p2) -> Double.compare(p2.getRating(), p1.getRating()))
                .limit(4)
                .collect(Collectors.toList());
        model.addAttribute("relatedProducts", relatedProducts);
        return "productDetail";
    }
}
