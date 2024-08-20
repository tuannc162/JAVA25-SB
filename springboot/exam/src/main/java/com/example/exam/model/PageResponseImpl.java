package com.example.exam.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponseImpl<T> implements PageResponse<T> {
    int currentPage;
    int pageSize;
    List<T> data;

    public PageResponseImpl(int currentPage, int pageSize, List<T> data) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.data = data;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int totalElements() {
        return data.size();
    }

    @Override
    public int totalPages() {
        return (int) Math.ceil((double) data.size() / pageSize);
    }

    @Override
    public List getContent() {
        int fromIndex = (currentPage - 1) * pageSize;
        if (fromIndex >= data.size()) {
            return List.of();
        }
        int toIndex = Math.min(fromIndex + pageSize, data.size());
        return data.subList(fromIndex, toIndex);
    }
}
