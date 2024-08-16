package com.example.homeworkDay12.dao.ipml;

import com.example.homeworkDay12.dao.BookDAO;
import com.example.homeworkDay12.database.BookDB;
import com.example.homeworkDay12.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> findAll() {
        return BookDB.books;
    }

    @Override
    public Book findById(int id) {
        return BookDB.books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> findByTitleContainsIgnoreCase(String keyword) {
        return BookDB.books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> sortBookByYear() {
        return BookDB.books.stream()
                .sorted((b1, b2) -> Integer.compare(b2.getYear(), b1.getYear())) // Sort descending by year
                .collect(Collectors.toList());
    }

}
