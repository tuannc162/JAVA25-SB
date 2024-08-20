package com.example.exam.utils;

import com.example.exam.model.Products;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonFileReader implements IFileReader {
    @Override
    public List<Products> readFile(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Products> products = new ArrayList<>();

        try {
            products = objectMapper.readValue(
                    new File(path),
                    new TypeReference<List<Products>>() {
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}
