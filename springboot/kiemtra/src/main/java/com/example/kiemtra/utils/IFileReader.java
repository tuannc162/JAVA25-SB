package com.example.kiemtra.utils;


import com.example.kiemtra.model.Products;

import java.util.List;

public interface IFileReader {
    List<Products> readFile(String path);
}
