package com.example.homeworkDay12.controller;

import com.example.homeworkDay12.model.Book;
import com.example.homeworkDay12.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

