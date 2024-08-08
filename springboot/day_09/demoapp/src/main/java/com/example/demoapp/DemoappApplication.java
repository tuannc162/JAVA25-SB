package com.example.demoapp;

import com.example.demoapp.model.Book;
import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoappApplication.class, args);

		Faker faker = new Faker();
		System.out.println(faker.name().fullName());

		Book book = new Book();
		book.setId(1);
		book.setTitle("Hahaaaaaaa");
		book.setAuthor("Hiiiiiiiiiiii");
		book.setYear(1999);

		System.out.println(book);

		// Khoi tao doi tuong Book su dung Builder
		Book book2 = Book.builder()
				.year(1999)
				.id(2)
				.title("kkkkkkk")
				.author("aaaaaaa")
				.build();
		System.out.println(book2);

	}
}
