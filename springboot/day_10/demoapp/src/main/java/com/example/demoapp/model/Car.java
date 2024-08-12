package com.example.demoapp.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;



@Component
public class Car implements Vehicle {
    @Override
    public void run() {
        System.out.println("Car is running");
    }
}
