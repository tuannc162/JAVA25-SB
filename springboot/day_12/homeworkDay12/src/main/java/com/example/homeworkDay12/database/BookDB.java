package com.example.homeworkDay12.database;

import com.example.homeworkDay12.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDB {
//    public static List<Book> books = new ArrayList<>();
    public static ArrayList <Book> books = new ArrayList<Book>(List.of(
        new Book(1, "Book1111", "ABC", 2002),
        new Book(2, "Book2222", "XYZ", 2023),
        new Book(3, "Book3333", "ABCXYZ", 2000)
));

}
