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
		Slugify slugify = Slugify.builder().build();

		for (int i = 0; i < 10; i++) {
			String name = faker.company().name();
			Service service = Service.builder()
					.serviceName(name)
					.description(faker.lorem().paragraph())
					.price(faker.number().randomDouble(2, 1, 1000)
					.isFree(faker.bool().bool())
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
//		Slugify slugify = Slugify.builder().build();

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
	void contextLoads() {
	}

}
