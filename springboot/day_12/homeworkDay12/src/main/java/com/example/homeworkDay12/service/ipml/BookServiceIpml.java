package com.example.homeworkDay12.service.ipml;

import com.example.homeworkDay12.database.BookDB;
import com.example.homeworkDay12.model.Book;
import com.example.homeworkDay12.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceIpml implements BookService {
    @Override
    public List<Book> getAllBook() {
        return BookDB.books;
    }

    @Override
    public Book findBookById(int id) {
        return BookDB.books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> findBooksByTitle(String keyword) {
        return BookDB.books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> BookByYear() {
        return BookDB.books.stream()
                .sorted((b1, b2) -> Integer.compare(b2.getYear(), b1.getYear())) // Sort descending by year
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findBookBeetweenYear(int startYear, int endYear) {
        return BookDB.books.stream()
                .filter(book -> book.getYear() >= startYear && book.getYear() <= endYear)
                .collect(Collectors.toList());
    }

}
