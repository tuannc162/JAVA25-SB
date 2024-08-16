package com.example.demoThymeleaf.controller;

import com.example.demoThymeleaf.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    private ArrayList<Book> books = new ArrayList<Book>(List.of(
            new Book(1, "Book1111", "ABC", 2002),
            new Book(2, "Book2222", "XYZ", 2023),
            new Book(3, "Book3333", "ABCXYZ", 2000)
    ));


    // GET: http://localhost:8080/books -> Hien thi "index.html"
    @GetMapping
    public String getAllBooks(Model model) {
        Book book = books.get(0);

        model.addAttribute("book", book);
        return "index";
    }
    @GetMapping("/blog")
    public String getBlogPage() {
        return "admin/blog";
    }
}
