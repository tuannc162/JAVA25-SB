package com.example.kiemtra.utils;

import com.example.kiemtra.model.Products;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonFileReader implements IFileReader {
    @Override
    public List<Products> readFile(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Products> product = null;
        try {
            product = objectMapper.readValue(
                    new File(path),
                    new TypeReference<List<Products>>() {
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return product;
    }
}
