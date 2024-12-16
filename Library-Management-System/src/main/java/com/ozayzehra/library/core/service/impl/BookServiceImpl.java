package com.ozayzehra.library.core.service.impl;

import java.util.*;
import java.util.concurrent.*;

import com.ozayzehra.library.core.dto.BookDTO;
import com.ozayzehra.library.core.service.BookService;
import com.ozayzehra.library.core.exception.NotFoundException;
import com.ozayzehra.library.data.entity.Book;
import com.ozayzehra.library.data.dao.BookDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {

    private final Map<String, Book> cache = new ConcurrentHashMap<>();

    @Autowired
    private BookDAO bookDAO;

    @Override
    public Book findBookByISBN(String isbn) throws Exception {
        Book book = cache.get(isbn);
        return book != null ? book : bookDAO.findBookByISBN(isbn);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) throws Exception {
        return bookDAO.findBooksByAuthor(author);
    }

    @Override
    public synchronized void addBook(BookDTO bookDTO) throws Exception {
        Book book = new Book();
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        bookDAO.save(book);
        cache.put(book.getIsbn(), book);
        String hey = "hey";
    }

    @Override
    public synchronized void removeBook(String isbn) throws Exception {
        Book book = bookDAO.findBookByISBN(isbn);

        bookDAO.deleteById(book.getId());
        cache.remove(isbn);
    }

    public synchronized void borrowBook(String isbn) throws Exception {
        Book book = findBookByISBN(isbn);
        if (book != null && book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookDAO.save(book);
            cache.put(isbn, book);
        }
    }

    public synchronized void returnBook(String isbn) throws Exception {
        Book book = findBookByISBN(isbn);
        if (book != null) {
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            bookDAO.save(book);
            cache.put(isbn, book);
        }
    }
}
