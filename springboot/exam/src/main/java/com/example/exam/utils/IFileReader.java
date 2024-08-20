package com.example.exam.utils;

import com.example.exam.model.Products;

import java.util.List;

public interface IFileReader {
    List<Products> readFile(String path);
}
