package com.example.demoapp.dao.ipml;

import com.example.demoapp.dao.BookDAO;
import com.example.demoapp.database.BookDB;
import com.example.demoapp.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Repository
public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> findAll() {
        return BookDB.books;
    }

    @Override
    public Book findById(int id) {
        for (Book book : BookDB.books) { //select * from books where id = ?
            if(book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> findByTitleContainsIgnoreCase(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : BookDB.books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> sortBookByYear() {
        BookDB.books.sort((o1, o2) -> o2.getYear() - o1.getYear());
        return BookDB.books;
    }


}
