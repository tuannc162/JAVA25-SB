package com.example.demoapp.controller;


import com.example.demoapp.model.Book;
import com.example.demoapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;



/*
* Controller: Là nơi tiếp nhận các request từ client, xử lý và trả về response
* @Controller: Các controller trả về Template (giao diện). Ngoài ra có thể trả về dữ liệu dạng JSON, XML,...
* @ResrController: Các controller trả về dữ liệu dạng JSON, XML,...
* @ResController = @Controller + @ResponseBody
* @ResponseBody: Chỉ trả về dữ liệu, không trả về Template. Dữ liệu trả về có thể là JSON, XML,...
* ResponseEmtity<?>: Class đại diện cho 1 đối tượng response, có thể set status code, header,...
 */


//@RestController
@Controller
@RequestMapping("/books")
public class BookController {


    // GET: http://localhost:8080/books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> result = bookService.getAllBook();
        return ResponseEntity.ok(result);
    }

    @Autowired
    private BookService bookService;
    @GetMapping("/{id}")
    // GET: http://localhost:8080/books/id
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookService.findBookById(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }




    // 1. Viết API sắp xếp book theo năm giảm dần
    // http://localhost:8080/books/sortByYear
    @GetMapping ("/sortByYear")
    @ResponseBody
    public ResponseEntity<List<Book>> sortByYear() {
        List<Book> result = bookService.BookByYear();
        return ResponseEntity.ok(result);
    }



    // 2. Tìm kiếm sách theo tên (trong tên chứa từ khóa bất kỳ)
    // http://localhost:8080/books/searchByTitle/{keyword}
    @GetMapping ("/searchByTitle/{keyword}")
    public ResponseEntity<List<Book>> searchByTitle(@PathVariable String keyword) {
        List<Book> result = bookService.findBooksByTitle(keyword);
        return ResponseEntity.ok(result);
    }


    // 3. Tìm kiếm sách được xuất bản trong khoảng thời gian (fromYear, toYear)
    // http://localhost:8080/books/searchByYear/fromYear/{fromYear}/toYear/{toYear}
    @GetMapping ("/searchByYear/fromYear/{fromYear}/toYear/{toYear}")
    public ResponseEntity<List<Book>> findByRangeYear(@PathVariable int fromYear, @PathVariable int toYear) {
        List<Book> result = bookService.findBookBeetweenYear(fromYear,toYear);
        return ResponseEntity.ok(result);
    }
}
