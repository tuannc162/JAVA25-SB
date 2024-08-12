package com.example.demoapp.controller;


import com.example.demoapp.model.Book;
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
    private ArrayList <Book> books = new ArrayList<Book>(List.of(
            new Book(1, "Book1111", "ABC", 2002),
            new Book(2, "Book2222", "XYZ", 2023),
            new Book(3, "Book3333", "ABCXYZ", 2000)
    ));

    // lấy danh sách tất cả các sách
//    @GetMapping // GET: http://localhost:8080/books
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED) //201
//    public List<Book> getAllBooks() {
//        return books;
//    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(books, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        for (Book book : books) {
            if(book.getId() == id) {
                return new ResponseEntity<>(book, HttpStatus.OK); //200
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
    }


    // Lấy sách theo id
    // http: //localhost:8080/books/1
    // http: //localhost:8080/books/2
//    @GetMapping("/{id}")
//    @ResponseBody
//    public Book getBookbyId(@PathVariable int id){
//        System.out.println("id = " + id);
//        for (Book book : books) {
//            if (book.getId() == id) {
//                return book;
//            }
//        }
//        return null;
//    }

    // 1. Viết API sắp xếp book theo năm giảm dần
    // http://localhost:8080/books/sortByYear
    @GetMapping ("/sortByYear")
    @ResponseBody
    public ResponseEntity<List<Book>> sortByYear() {
        books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getYear() - o1.getYear();
            }
        });
        return ResponseEntity.ok(books);
    }



    // 2. Tìm kiếm sách theo tên (trong tên chứa từ khóa bất kỳ)
    // http://localhost:8080/books/searchByTitle/{keyword}
    @GetMapping ("/searchByTitle/{keyword}")
    public ResponseEntity<List<Book>> searchByTitle(@PathVariable String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return ResponseEntity.ok(result);
    }


    // 3. Tìm kiếm sách được xuất bản trong khoảng thời gian (fromYear, toYear)
    // http://localhost:8080/books/searchByYear/fromYear/{fromYear}/toYear/{toYear}
    @GetMapping ("/searchByYear/fromYear/{fromYear}/toYear/{toYear}")
    public ResponseEntity<List<Book>> findByRangeYear(@PathVariable int fromYear, @PathVariable int toYear) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() >= fromYear && book.getYear() <= toYear) {
                result.add(book);
            }
        }
        return ResponseEntity.ok(result);
    }
}
