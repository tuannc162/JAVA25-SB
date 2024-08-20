package com.example.demoapp.service;

import  com.example.demoapp.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBook();

    Book findBookById (int id);

    List<Book> findBooksByTitle (String keyword);

    List<Book> BookByYear ();

    List<Book> findBookBeetweenYear(int startYear, int endYear);

}
