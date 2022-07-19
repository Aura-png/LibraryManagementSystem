package com.example.project2;

import com.example.project2.entities.Book;
import com.example.project2.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@SpringBootApplication
public class Project2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Project2Application.class, args);
	}

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public void run(String... args) throws Exception {
		Book book1 = Book.builder().title("title1")
				.author("author1")
				.genre("classic").build();
		Book book2 = Book.builder().title("title2")
				.author("author2")
				.genre("classic").build();
		Book book3 = Book.builder().title("title3")
				.author("author3")
				.genre("classic").build();
		//adminRepository.addToLibraryContent(book1);
		//adminRepository.addToLibraryContent(book2);
		//adminRepository.addToLibraryContent(book3);
		adminRepository.showBooks();
	}
}
