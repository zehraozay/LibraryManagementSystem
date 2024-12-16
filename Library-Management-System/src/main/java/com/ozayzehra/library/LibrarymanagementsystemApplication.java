package com.ozayzehra.library;

import com.ozayzehra.library.core.dto.BookDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ozayzehra.library.data.entity.Book;
import com.ozayzehra.library.core.service.BookService;

@SpringBootApplication
public class LibrarymanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarymanagementsystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate(BookService bookService) {
		return (args) -> {

			BookDTO book = new BookDTO("TestIsbn", "TestName", "AuthZ",2012, 5);
			bookService.addBook(book);

			BookDTO book1 = new BookDTO("TestIsbn1", "TestName1", "AuthZ", 2020, 4);
			bookService.addBook(book1);

			BookDTO book2 = new BookDTO("TestIsbn2", "TestName2", "AuthY", 2022, 2);
			bookService.addBook(book2);

		};
	}
}
