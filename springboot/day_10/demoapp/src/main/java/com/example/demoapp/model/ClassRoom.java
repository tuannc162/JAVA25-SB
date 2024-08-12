package com.example.demoapp.model;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassRoom {
    // Cách 1: Field-based Injection
//    @Autowired
//    Student student;
//    @Autowired
//    Teacher teacher;

    Student student;
    Teacher teacher;
    // Cách 2: Constructor-based Injection
    public ClassRoom(Student student, @Qualifier("teacher2") Teacher teacher) {
        this.student = student;
        this.teacher = teacher;
    }
}
