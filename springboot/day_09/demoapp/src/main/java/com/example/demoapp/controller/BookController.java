package com.example.demoapp.controller;


import com.example.demoapp.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;



/*
* Controller: Là nơi tiếp nhận các request từ client, xử lý và trả về response
* @Controller: Các controller trả về Template (giao diện). Ngoài ra có thể trả về dữ liệu dạng JSON, XML,...
* @ResrController: Các controller trả về dữ liệu dạng JSON, XML,...
 */


@RestController
@RequestMapping("/books")
public class BookController {
    private ArrayList <Book> books = new ArrayList<Book>(List.of(
            new Book(1, "Book1111", "ABC", 2002),
            new Book(2, "Book2222", "XYZ", 2023),
            new Book(3, "Book3333", "ABCXYZ", 2000)
    ));

    // lấy danh sách tất cả các sách
    @GetMapping // GET: http://localhost:8080/books
    public List<Book> getAllBooks() {
        return books;
    }

    // Lấy sách theo id
    // http: //localhost:8080/books/1
    // http: //localhost:8080/books/2
    @GetMapping("/{id}")
    public Book getBookbyId(@PathVariable int id){
        System.out.println("id = " + id);
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

}
