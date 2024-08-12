package com.homework.homework.controller;

import com.homework.homework.model.Products;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductsController {
    public List<Products> products = new ArrayList<Products>(List.of(
            new Products(1, "iPhone 14 Pro", "Verry Good!", 999, "Apple"),
            new Products(2, "iPhone 14 Pro Max", "Verry Good!", 799, "Apple"),
            new Products(3, "iPhone 15 Pro", "Verry Good!", 699, "Apple"),
            new Products(4, "iPhone 15 Pro max", "Verry Good!", 729, "Apple"),
            new Products(5, "Samsung Galaxy S21", "Verry Good!", 649, "Samsung"),
            new Products(6, "Samsung Galaxy S22", "Verry Good!", 899, "Samsung"),
            new Products(7, "Samsung Galaxy S23", "Verry Good!", 499, "Samsung"),
            new Products(8, "Xiaomi Note 15", "Verry Good!", 799, "Xiaomi"),
            new Products(9, "Xiaomi Note 16", "Verry Good!", 599, "Xiaomi"),
            new Products(10, "Oppo S20", "Verry Good!", 249, "Oppo")
    ));


    // 1. Lấy thông tin chi tiết của một sản phẩm
    // http://localhost:8080/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable int id) {
        for (Products product : products) {
            if(product.getId() == id) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // 2. Lấy sản phẩm với tên bắt đầu bằng prefix nào đó
    // http://localhost:8080/products/name-start/{prefix}
    @GetMapping("/name-start/{prefix}")
    public ResponseEntity<List<Products>> getProductByPrefix (@PathVariable String prefix) {
        List<Products> result = new ArrayList<>();
        for (Products product : products) {
            if (product.getName().toLowerCase().contains(prefix.toLowerCase())) {
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }


    // 3. Lọc sản phẩm theo khoảng giá
    @GetMapping("/price/{min}/{max}")
    public ResponseEntity<List<Products>> getProductsByPrice(@PathVariable int min, @PathVariable int max) {
        List<Products> filterProducts = products.stream()
                .filter(product -> product.getPrice() >= min && product.getPrice() <= max)
                .collect(Collectors.toList());
        if (filterProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(filterProducts, HttpStatus.OK);
    }


    // 4. Lấy sản phẩm theo thương hiệu
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Products>> getProductsByBrand(@PathVariable String brand) {
        List<Products> filterProducts = products.stream()
                .filter(product -> product.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
        if (filterProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(filterProducts, HttpStatus.OK);
    }


    // 5. Lấy sản phẩm có giá cao nhất theo thương hiệu
    @GetMapping("/brand/{brand}/max-price")
    public ResponseEntity<Products> getProductByMaxPrice(@PathVariable String brand) {
        return products.stream()
                .filter(product -> product.getBrand().equalsIgnoreCase(brand))
                .max((p1, p2) -> Integer.compare(p1.getPrice(), p2.getPrice()))
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


}
