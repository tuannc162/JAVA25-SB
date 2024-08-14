package com.example.demoapp.utils;

import com.example.demoapp.model.Book;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component // sử dụng thư viện OpenCSV
public class CsvFileReader implements IFileReader{
    @Override
    public List<Book> readFile(String path) {
        List<Book> books = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> records = reader.readAll();
            boolean isFirstLine = true;

            for (String[] record : records) {
                if (isFirstLine) {
                    // Bỏ qua dòng đầu tiên (dòng tiêu đề)
                    isFirstLine = false;
                    continue;
                }

                Book book = Book.builder()
                        .id(Integer.parseInt(record[0]))
                        .title(record[1])
                        .author(record[2])
                        .year(Integer.parseInt(record[3]))
                        .build();
                books.add(book);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return books;
    }
}
