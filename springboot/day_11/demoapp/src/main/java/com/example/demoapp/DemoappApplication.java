package com.example.demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/*
* Bean là gì?:
	- Được coi là thành phần xương sống của ứng dụng Spring
	- Là đối tượng được khởi tạo, lắp ráp, quản lý bởi Spring IoC container
* Tạo ra Bean như thế nào?
	- Sử dụng các annotation đánh dấu lên class (class level): @Compoment, @Controller, @Rescontroller,...
	- Sử dụng annotation @Bean trên method (method level) trong class @Còniguration
* Sử dụng Bean như thế nào?
	- Bean thường được sử dụng trong 1 Bean khác (dependency) (A->B) (A là cha, B là con)
	- 3 cách sử dụng Bean:
		+ Field-based Injection
		+ Constructor-based Injection
		+ Setter-based Injection
 */





@SpringBootApplication
public class DemoappApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoappApplication.class, args);

	}
}
