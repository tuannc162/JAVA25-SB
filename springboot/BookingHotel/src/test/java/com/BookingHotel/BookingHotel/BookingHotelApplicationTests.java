package com.BookingHotel.BookingHotel;

import com.BookingHotel.BookingHotel.entity.*;
import com.BookingHotel.BookingHotel.model.enums.UserRole;
import com.BookingHotel.BookingHotel.repository.*;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class BookingHotelApplicationTests {
	@Autowired
	private AmenityRepository amenityRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private HotelRepository	hotelRepository;

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PromotionRepository promotionRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RoomTypeRepository roomTypeRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private RoomPriceRepository roomPriceRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	@Test
	void encode_customer_password(){
		List<Customer> customers = customerRepository.findAll();
		for (Customer cus : customers) {
			cus.setPassword(passwordEncoder.encode("123"));
			customerRepository.save(cus);
		}
	}

	@Test
	void save_customers(){
		Faker faker = new Faker();
		for (int i = 0; i < 50; i++) {
			String name = faker.name().fullName();
			Customer customer = Customer.builder()
					.name(name)
					.email(faker.internet().emailAddress())
					.avatar("https://placehold.co/200x200?text=" + name.substring(0, 1).toUpperCase())
					.password("123")
					.role(i == 0 || i == 1 ? UserRole.ADMIN : UserRole.CUSTOMER)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();
			customerRepository.save(customer);
		}
	}


	@Test
	void save_regions(){
		Faker faker = new Faker();
		Slugify slugify = Slugify.builder().build();
		for (int i = 0; i < 10; i++) {
			String name = faker.country().name();
			Region region = Region.builder()
					.regionName(name)
					.slug(slugify.slugify(name))
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();
			regionRepository.save(region);
		}
	}

	@Test
	void save_blogs() {
		Faker faker = new Faker();
		Random rd = new Random();
		Slugify slugify = Slugify.builder().build();

		List<Customer> customers = customerRepository.findByRole(UserRole.ADMIN);

		for (int i = 0; i < 100; i++) {
			// random 1 user
			try {
				Customer rdCustomer = customers.get(rd.nextInt(customers.size()));

				String title = faker.book().title();
				boolean status = rd.nextInt(2) == 0;
				Blog blog = Blog.builder()
						.title(title)
						.slug(slugify.slugify(title))
						.description(faker.lorem().paragraph())
						.content(faker.lorem().paragraph(100))
						.thumbnail(generateLinkImage(title))
						.status(status)
						.createdAt(LocalDateTime.now())
						.updatedAt(LocalDateTime.now())
						.publishedAt(status ? LocalDateTime.now() : null)
						.customer(rdCustomer)
						.build();
				blogRepository.save(blog);
			} catch (Exception e) {
				System.err.println("Error saving blog: " + e.getMessage());
			}
		}
	}

	private String generateLinkImage(String name) {
		return "https://placehold.co/200x200?text=" + name.substring(0, 1).toUpperCase();
	}

	@Test
	void save_images() {
		Faker faker = new Faker();

		for (int i = 0; i < 100; i++) {
			Image image = Image.builder()
					.url("https://example.com/image" + i + ".jpg")
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();
			imageRepository.save(image);
		}
	}

	@Test
	void save_services() {
		Faker faker = new Faker();
		Boolean status = faker.bool().bool();

		for (int i = 0; i < 10; i++) {
			String name = faker.company().name();
			Service service = Service.builder()
					.serviceName(name)
					.description(faker.lorem().paragraph())
					.price(faker.number().numberBetween(100, 500))
					.status(status)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();
			serviceRepository.save(service);
		}
	}

	@Test
	void save_hotels() {
		Faker faker = new Faker();
		Random rd = new Random();

		List<Region> regions = regionRepository.findAll();
		List<Service> services = serviceRepository.findAll();
		List<Image> images = imageRepository.findAll();

		for (int i = 0; i < 100; i++) {
			// Random 1 region
			Region rdRegion = regions.get(rd.nextInt(regions.size()));

			// Random 1-4 services
			List<Integer> rdServices = new ArrayList<>();
			int numberOfServices = rd.nextInt(4) + 1;
			while (rdServices.size() < numberOfServices) {
				Service service = services.get(rd.nextInt(services.size()));
				if (!rdServices.contains(service.getServiceId())) {
					rdServices.add(service.getServiceId());
				}
			}

			// Random 1-5 images
			List<String> rdImages = new ArrayList<>();
			int numberOfImages = rd.nextInt(5) + 1;
			while (rdImages.size() < numberOfImages) {
				Image image = images.get(rd.nextInt(images.size()));
				if (!rdImages.contains(image.getUrl())) {
					rdImages.add(image.getUrl());
				}
			}

			String name = faker.company().name();
			Hotel hotel = Hotel.builder()
					.name(name)
					.address(faker.address().fullAddress())
					.phone(faker.phoneNumber().phoneNumber())
					.email(faker.internet().emailAddress())
					.rating(rd.nextInt(5) + 1)
					.description(faker.lorem().paragraph())
					.policy(faker.lorem().paragraph())
					.mainImage(generateLinkImage(name))
					.subImages(rdImages)
					.services(rdServices)
					.region(rdRegion)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();

			hotelRepository.save(hotel);
		}
	}

	@Test
	void save_amenity() {
		Faker faker = new Faker();

		for (int i = 0; i < 10; i++) {
			String name = faker.company().name();
			Amenity amenity =Amenity.builder()
					.name(name)
					.description(faker.lorem().paragraph())
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();

			amenityRepository.save(amenity);
		}
	}

	@Test
	void save_reviews() {
		Faker faker = new Faker();
		Random rd = new Random();

		List<Hotel> hotels = hotelRepository.findAll();
		List<Customer> customers = customerRepository.findAll();

		for (Hotel hotel : hotels) {
			// Create 10 -> 20 reviews for each movie
			for (int i = 0; i < rd.nextInt(11) + 10; i++) {
				// Random 1 user
				Customer rdCus = customers.get(rd.nextInt(customers.size()));

				Review review = Review.builder()
						.comment(faker.lorem().paragraph())
						.rating(rd.nextInt(6) + 5)
						.hotel(hotel)
						.customer(rdCus)
						.createdAt(LocalDateTime.now())
						.updatedAt(LocalDateTime.now())
						.build();
				reviewRepository.save(review);
			}
		}
	}

	@Test
	void save_roomType() {
		Faker faker = new Faker();

		for (int i = 0; i < 10; i++) {
			String name = faker.leagueOfLegends().champion();
			RoomType roomType =RoomType.builder()
					.name(name)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();

			roomTypeRepository.save(roomType);
		}
	}

	@Test
	void save_room() {
		Faker faker = new Faker();
		Random rd = new Random();

		List<Amenity> amenities = amenityRepository.findAll();
		List<Image> images = imageRepository.findAll();

		for (int i = 0; i < 100; i++) {
			// Random 1-5 images
			List<String> rdImages = new ArrayList<>();
			int numberOfImages = rd.nextInt(5) + 1;
			while (rdImages.size() < numberOfImages) {
				Image image = images.get(rd.nextInt(images.size()));
				if (!rdImages.contains(image.getUrl())) {
					rdImages.add(image.getUrl());
				}
			}

			// Random 1-4 services
			List<Integer> rdAmenity = new ArrayList<>();
			int numberOfAmenity = rd.nextInt(4) + 1;
			while (rdAmenity.size() < numberOfAmenity) {
				Amenity amenity = amenities.get(rd.nextInt(amenities.size()));
				if (!rdAmenity.contains(amenity.getAmenityId())) {
					rdAmenity.add(amenity.getAmenityId());
				}
			}

			String name = faker.company().name();
			Room room = Room.builder()
					.roomNumber(faker.number().digits(3))
					.availability(faker.bool().bool())
					.description(faker.lorem().paragraph())
					.mainImage(generateLinkImage(name))
					.subImage(rdImages)
					.numberOfGuests(rd.nextInt(5) + 1)
					.amenity(rdAmenity)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();

			roomRepository.save(room);
		}
	}

	@Test
	void save_booking() {
		Faker faker = new Faker();
		Random rd = new Random();

		List<Room> rooms = roomRepository.findAll();
		List<Customer> customers = customerRepository.findAll();
		List<Hotel> hotels = hotelRepository.findAll();

		for (int i = 0; i < 100; i++) {
			// Random check-in date (từ hôm nay đến 30 ngày tới)
			LocalDateTime checkInDate = LocalDateTime.now().plusDays(rd.nextInt(30) + 1);
			// Random check-out date (từ 1 đến 7 ngày sau check-in)
			LocalDateTime checkOutDate = checkInDate.plusDays(rd.nextInt(7) + 1);

			// Tính tổng số tiền (giả định giá mỗi đêm là 100 - 500)
			BigDecimal totalAmount = BigDecimal.valueOf(rd.nextInt(401) + 100)
					.multiply(BigDecimal.valueOf((checkOutDate.toLocalDate().toEpochDay() - checkInDate.toLocalDate().toEpochDay()))); // Tổng tiền

			// Random room, customer, hotel
			Room room = rooms.get(rd.nextInt(rooms.size()));
			Customer customer = customers.get(rd.nextInt(customers.size()));
			Hotel hotel = hotels.get(rd.nextInt(hotels.size()));

			Booking booking = Booking.builder()
					.checkInDate(checkInDate)
					.checkOutDate(checkOutDate)
					.totalAmount(totalAmount)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.room(room)
					.customer(customer)
					.hotel(hotel)
					.build();

			bookingRepository.save(booking);
		}
	}

	@Test
	void save_roomPrice() {
		Faker faker = new Faker();
		Random rd = new Random();

		List<Room> rooms = roomRepository.findAll();

		for (int i = 0; i < 101; i++) {
			// Tạo giá cơ bản và giá bổ sung ngẫu nhiên
			Integer basePrice = faker.number().numberBetween(100, 1000);
			Integer additionalPrice = faker.number().numberBetween(10, 100);

			// Ngày hiệu lực và ngày kết thúc
			LocalDateTime effectiveDate = LocalDateTime.now().plusDays(rd.nextInt(30));
			LocalDateTime endDate = effectiveDate.plusDays(rd.nextInt(30) + 1); // Ngày kết thúc sau ngày hiệu lực

			// Chọn một phòng ngẫu nhiên
			Room room = rooms.get(rd.nextInt(rooms.size()));

			RoomPrice roomPrice = RoomPrice.builder()
					.basePrice(basePrice)
					.additionalPrice(additionalPrice)
					.effectiveDate(effectiveDate)
					.endDate(endDate)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.room(room)
					.build();

			roomPriceRepository.save(roomPrice);
		}
	}

	@Test
	void save_order() {
		Faker faker = new Faker();
		Random rd = new Random();

		List<Booking> bookings = bookingRepository.findAll();
		List<Service> services = serviceRepository.findAll();

		for (int i = 0; i < 100; i++) {
			// Random số dịch vụ (1 đến 4 dịch vụ)
			List<Integer> rdServices = new ArrayList<>();
			int numberOfServices = rd.nextInt(4) + 1;
			while (rdServices.size() < numberOfServices) {
				Service service = services.get(rd.nextInt(services.size()));
				if (!rdServices.contains(service.getServiceId())) {
					rdServices.add(service.getServiceId());
				}
			}

			// Ngày phát hành
			LocalDateTime issueDate = LocalDateTime.now();

			// Tính tổng số tiền (giả định giá mỗi dịch vụ là ngẫu nhiên từ 50 đến 200)
			BigDecimal totalAmount = rdServices.stream()
					.map(serviceId -> {
						Service service = services.stream()
								.filter(s -> s.getServiceId().equals(serviceId))
								.findFirst()
								.orElse(null);
						return service != null ? BigDecimal.valueOf(faker.number().numberBetween(50, 200)) : BigDecimal.ZERO;
					})
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			// Chọn một booking ngẫu nhiên
			Booking booking = bookings.get(rd.nextInt(bookings.size()));

			Order order = Order.builder()
					.services(rdServices)
					.issueDate(issueDate)
					.totalAmount(totalAmount)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.booking(booking)
					.build();

			orderRepository.save(order);
		}
	}

	@Test
	void save_promotion() {
		Faker faker = new Faker();
		Random rd = new Random();

		List<Room> rooms = roomRepository.findAll();
		List<Hotel> hotels = hotelRepository.findAll();

		for (int i = 0; i < 100; i++) {
			// Random roomList (chọn ngẫu nhiên từ 1 đến 5 phòng)
			List<Integer> roomList = new ArrayList<>();
			int numberOfRooms = rd.nextInt(5) + 1;
			while (roomList.size() < numberOfRooms) {
				Room room = rooms.get(rd.nextInt(rooms.size()));
				if (!roomList.contains(room.getRoomId())) {
					roomList.add(room.getRoomId());
				}
			}

			String promotionName = faker.commerce().promotionCode(); // Tạo tên khuyến mãi ngẫu nhiên
			String description = faker.lorem().paragraph(); // Mô tả ngẫu nhiên
			Integer discountRate = rd.nextInt(50) + 1; // Tỷ lệ giảm giá từ 1 đến 50%
			LocalDateTime startDate = LocalDateTime.now().plusDays(rd.nextInt(30)); // Ngày bắt đầu
			LocalDateTime endDate = startDate.plusDays(rd.nextInt(30) + 1); // Ngày kết thúc

			// Chọn một khách sạn ngẫu nhiên
			Hotel hotel = hotels.get(rd.nextInt(hotels.size()));

			Promotion promotion = Promotion.builder()
					.roomList(roomList)
					.promotionName(promotionName)
					.description(description)
					.discountRate(discountRate)
					.startDate(startDate)
					.endDate(endDate)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.hotel(hotel)
					.build();

			promotionRepository.save(promotion);
		}
	}



	@Test
	void contextLoads() {
	}

}
