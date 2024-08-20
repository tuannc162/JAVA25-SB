package com.example.exam.controller;


import com.example.exam.model.PageResponse;
import com.example.exam.model.PageResponseImpl;
import com.example.exam.model.Products;
import com.example.exam.utils.IFileReader;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private final IFileReader fileReader;
    @Autowired
    public ProductsController(IFileReader fileReader) {
        this.fileReader = fileReader;
    }

//    public ProductsController() {
//        Faker faker = new Faker();
//        int numberName = 10;
//        List<String> name = new ArrayList<>();
//
//        for (int i = 0; i < numberName; i++) {
//            name.add(faker.pr().author());
//        }
//
//        for (int i = 0; i <= 99; i++) {
//            String description = description.get(faker.number().numberBetween(0, numberName));
//
//            LocalDate createdDate = faker.date().past(3650, TimeUnit.DAYS).toInstant()
//                    .atZone(ZoneId.systemDefault()).toLocalDate();
//
//            Products product = Products.builder()
//                    .id(i)
//                    .name(faker.book().title())
//                    .description(description)
//                    .price(faker.number().numberBetween(1000, 10000))
//                    .rating(createdDate)
//                    .build();
//
//            books.add(book);
//        }
//    }


    // http://localhost:8080 -> Hiển thị template "index.html"
    @GetMapping
    public String getHomePage(Model model, @RequestParam(required = false) String name) {
        List<Products> products = fileReader.readFile("products.json");

        if(name != null) {
            products = products.stream()
                    .filter(b -> b.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        } else {
            products = products.stream()
                    .limit(10)
                    .collect(Collectors.toList());
        }
        model.addAttribute("products", products);
        return "index";
    }



    // http://localhost:8080/products
    // http://localhost:8080/products?page=1&size=10
    @GetMapping("/products")
    public String getProductsPage(Model model,
                                  @RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = "10") int size) {
        List<Products> products = fileReader.readFile("products.json");

        PageResponse<Products> pageResponse = PageResponseImpl.<Products>builder()
                .currentPage(page)
                .pageSize(size)
                .data(products)
                .build();

        model.addAttribute("pageResponse", pageResponse);
        return "index";
    }

    @GetMapping("/products/{id}")
    public String getProductDetail(@PathVariable int id, Model model) {
        List<Products> products = fileReader.readFile("products.json");
        Products product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        model.addAttribute("product", product);

        // Tìm sản phẩm liên quan
        List<Products> relatedProducts = products != null
                ? products.stream()
                .filter(p -> p.getId() != product.getId())
                .sorted((p1, p2) -> Double.compare(p2.getRating(), p1.getRating()))
                .limit(4)
                .collect(Collectors.toList())
                : List.of();

        model.addAttribute("relatedProducts", relatedProducts);

        return "productDetail";

    }
}
