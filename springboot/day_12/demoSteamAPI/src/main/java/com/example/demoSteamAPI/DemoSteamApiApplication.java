package com.example.demoSteamAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/*
*
*
*
 */




@SpringBootApplication
public class DemoSteamApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSteamApiApplication.class, args);

		//C1: Sử dụng class implement Greeting
		Greeting vietNam = new VietNam();
		vietNam.sayHello("Huy");

		//C2; Sử dụng Anonymos Class
		Greeting english = new Greeting() {
			@Override
			public void sayHello(String name) {
				System.out.println("Hello " + name);
			}

		};
		english.sayHello("An");

		//C3: Sử dụng Lamda expression
		Greeting japan = (name) -> {
			System.out.println("Xin chào bằng Tiếng Nhật " + name);
		};
		japan.sayHello("Tuấn");



		// Tính tổng 2 số
		Calculator sum = (a, b) -> {
			return (a+b);
		};
		System.out.println("Sum = " +sum.calculator(10,20));

		// Tính hiệu 2 số
		Calculator subtraction = (a, b) -> {
			return (a-b);
		};
		System.out.println("Subtraction = " +subtraction.calculator(20,10));

		// Thao tác với List
//		List<Integer> numbers = new ArrayList<>(List.of(3, 54, 7, 12, 9, 2, 10));
//		numbers.forEach((number) -> System.out.println(number));
//
//		System.out.println("Xóa số lẻ");
//		numbers.removeIf((number) -> number % 2 == 1);
//		numbers.forEach((number) -> System.out.println(number));
//
//		System.out.println("Sắp xếp");
//		numbers.sort((a, b) -> a - b);
//		numbers.forEach((number) -> System.out.println(number));


		// Stream API
		List<Integer> numbers = new ArrayList<>(List.of(3, 54, 7, 12, 9, 2));
		int total = numbers.stream()
				.map(number -> number*2)
				.reduce(0, (a, b) -> a + b);
		System.out.println("Tổng = " +total);


		// Tìm giá trị số chẵn lớn nhất
		int max = numbers.stream()
				.filter((number) -> number % 2 == 0)
				.max((a ,b) -> a - b)
				.orElse(0);
		System.out.println("Max = " +max);

		int max1 = numbers.stream()
				.filter((number) -> number % 2 == 0)
				.mapToInt(number -> number)
				.max()
				.orElse(0);
		System.out.println("Max1 = " +max1);


		// Bài tập
		List<Integer> newNumbers = new ArrayList<>(List.of(10, 5, 3, 12, 6, 7, 5, 3));

		// 7. Lấy danh sách các phần tử không trùng nhau (sử dụng distinct)
		List<Integer> distinctNumbers = numbers.stream()
				.distinct()
				.toList();
		System.out.println("7. Danh sách các phần tử không trùng nhau: " + distinctNumbers);

		// 8. Lấy 5 phần tử đầu tiên trong mảng (sử dụng limit)
		List<Integer> firstFiveNumbers = numbers.stream()
				.limit(5)
				.toList();
		System.out.println("8. 5 phần tử đầu tiên trong mảng: " + firstFiveNumbers);

		// 9. Lấy phần tử từ thứ 3 -> thứ 5 (sử dụng limit + skip)
		List<Integer> thirdToFivethNumbers = numbers.stream()
				.skip(2) // Bỏ qua 2 phần tử đầu
				.limit(3) // Lấy 3 phần tử tiếp theo
				.toList();
		System.out.println("9. Phần tử thứ 3 đến thứ 5: " + thirdToFivethNumbers);

		// 10. Lấy phần tử đầu tiên > 5 (sử dụng findFirst)
		Integer firstNumberThanFive = numbers.stream()
				.filter(n -> n > 5)
				.findFirst()
				.orElse(null);
		System.out.println("10. Phần tử đầu tiên lớn hơn 5: " + firstNumberThanFive);

		// 11. Kiểm tra xem list có phải là list chẵn hay không (sử dụng allMatch)
		boolean allEven = numbers.stream()
				.allMatch(n -> n % 2 == 0);
		System.out.println("11. Kiểm tra List trên có phải là List số chẵn hay không?: " + allEven);

		// 12. Kiểm tra xem list có phần tử > 10 hay không (sử dụng anyMatch)
		boolean checkNumberThanTen = numbers.stream()
				.anyMatch(n -> n > 10);
		System.out.println("12. Kiểm tra xem List có phần tử nào lớn hơn 10 hay không?: " + checkNumberThanTen);

		// 13. Có bao nhiêu phần tử > 5 (sử dụng count)
		long countGreaterThanFive = numbers.stream()
				.filter(n -> n > 5)
				.count();
		System.out.println("13. Có bao nhiêu phần tử lớn hơn 5: " + countGreaterThanFive);

		// 14. Nhân đôi các phần tử trong list và trả về list mới (sử dụng map)
		List<Integer> doubledNumbers = numbers.stream()
				.map(n -> n * 2)
				.toList();
		System.out.println("14. Nhân 2 các phần tử trong List ta được List mới: " + doubledNumbers);

		// 15. Kiểm tra xem list không chứa giá trị nào = 8 hay không (sử dụng noneMatch)
		boolean noneEqualsEight = numbers.stream()
				.noneMatch(n -> n == 8);
		System.out.println("15. Kiểm tra trong List xem có phần tử nào có giá trị = 8 hay không?: " + noneEqualsEight);



	}
}
