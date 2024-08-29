package com.example.movie_app;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.enums.MovieType;
import com.example.movie_app.repository.MovieRepository;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@SpringBootTest
class MovieAppApplicationTests {
	@Autowired
	private MovieRepository movieRepository;


	@Test
	void save_movies() {
		Faker faker = new Faker();
		Slugify slugify = Slugify.builder().build();
		Random rd = new Random();

		for(int i = 0; i < 150; i++) {
			String name = faker.name().fullName();
			Boolean status = faker.bool().bool();
			Movie movie = Movie.builder()
					.name(name)
					.slug(slugify.slugify(name))
					.description(faker.lorem().paragraph())
					.poster("https://placehold.co/600x400?text=T" + name.substring(0, 1).toUpperCase())
					.releaseYear(faker.number().numberBetween(1990, 2022))
					.rating(faker.number().randomDouble(1, 6, 10))
					.trailerUrl("https://www.youtube.com/embed/gCUg6Td5fgQ?si=OCtNkpFF03gq03ny")
					.type(MovieType.values()[rd.nextInt(MovieType.values().length)])
					.status(status)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.publishedAt(status ? LocalDateTime.now() : null)
					.build();
			movieRepository.save(movie);
		}
	}

	@Test
	void test_methods() {
		List<Movie> movies = movieRepository.findAll();
		System.out.println("Movies size: " + movies.size());

		Movie movie1 = movieRepository.findById(1).orElse(null);
		System.out.println("Movie 1: " + movie1);

		List<Movie> moviesByIds = movieRepository.findAllById(List.of(1, 2, 3));
		System.out.println("Movies by ids: " + moviesByIds);

		//Update
		movie1.setName("Chúa tể những chiếc nhẫn");
		movieRepository.save(movie1);

		//Delete
//		movieRepository.delete(movie1); //delete from movies where id = ?
//		movieRepository.deleteById(2);
//		movieRepository.deleteAllById(List.of(1, 2, 3));
//		movieRepository.deleteAll();

	}

	@Test
	public void testMethodQuery() {
		long countPhimBo = movieRepository.countByType(MovieType.PHIM_BO);
		System.out.println("Phim bo: " + countPhimBo);

		List<Movie> films = movieRepository.findTop5ByTypeAndStatusOrderByCreatedAtDescRatingAsc(MovieType.PHIM_LE, true);
		System.out.println("Films: " + films);
	}

}
