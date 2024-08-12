package com.example.demoapp;


import com.example.demoapp.model.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitBean {
    @Bean(name = "teacher1")
    public Teacher teacher1() {
        return new Teacher(1, "Nguyễn Văn A");
    }

    @Bean(name = "teacher2")
    public Teacher teacher2() {
        return new Teacher(2, "Trần Văn B");
    }
}
