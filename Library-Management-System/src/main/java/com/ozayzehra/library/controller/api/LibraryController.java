package com.ozayzehra.library.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import com.ozayzehra.library.controller.request.RequestBook;
import com.ozayzehra.library.controller.mapper.BookMapper;

import com.ozayzehra.library.data.entity.Book;
import com.ozayzehra.library.core.service.BookService;


import java.util.List;

@Transactional
@RestController(value = "com.ozayzehra.library.controller.api.LibraryController")
public class LibraryController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/book/remove")
    public String removeBook(@Param("isbn") String isbn) {
        try {
            bookService.removeBook(isbn);
            return "removed";
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/book/find-by-isbn")
    public Book findBookByISBN(@Param("isbn") String isbn) {
        try {
            final Book book = bookService.findBookByISBN(isbn);
            return book;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    @RequestMapping("/book/find-by-author")
    public List<Book> findBooksByAuthor(@Param("author") String author) {

        try {
            final List<Book> books = bookService.findBooksByAuthor(author);
            return books;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/book/add")
    public String createBook(@RequestBody RequestBook bookRequest) {
        try {
            bookService.addBook(BookMapper.convertBookApiRequestToInputDTO(bookRequest));
            return "book added";
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/book/borrow/{isbn}")
    public void borrowBook(@PathVariable String isbn) {

        try {
            bookService.borrowBook(isbn);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }


    }

    @PutMapping("/book/return/{isbn}")
    public void returnBook(@PathVariable String isbn) {

        try {
            bookService.returnBook(isbn);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }
}