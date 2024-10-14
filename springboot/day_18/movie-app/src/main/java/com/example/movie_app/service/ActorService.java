package com.example.movie_app.service;

import com.example.movie_app.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;

    public Object getAllActors() {
        return actorRepository.findAll();
    }
}
