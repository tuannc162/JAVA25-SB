package com.example.homeworkDay12.dao;

import com.example.homeworkDay12.model.Book;

import java.util.List;

public interface BookDAO {

    List<Book> findAll();

    Book findById (int id);

    List<Book> findByTitleContainsIgnoreCase(String keyword);

    List<Book> sortBookByYear ();
}
