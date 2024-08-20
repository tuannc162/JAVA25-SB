package com.example.kiemtra.model;

import java.util.List;

public interface PageResponse<T> {
    int getCurrentPage();

    int getPageSize();

    int totalElements();

    int totalPages();

    List<T> getContent();
}
