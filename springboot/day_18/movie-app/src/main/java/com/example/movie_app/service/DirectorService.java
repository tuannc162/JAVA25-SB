package com.example.movie_app.service;

import com.example.movie_app.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;

    public Object getAllDirectors() {
        return directorRepository.findAll();
    }
}
