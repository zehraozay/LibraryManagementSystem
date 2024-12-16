package com.ozayzehra.library.core.service;

import com.ozayzehra.library.core.dto.BookDTO;
import com.ozayzehra.library.core.exception.NotFoundException;
import com.ozayzehra.library.data.entity.Book;

import java.util.List;

public interface BookService {

    void addBook(BookDTO bookDTO) throws Exception;

    void removeBook(String isbn) throws Exception;

    Book findBookByISBN(String isbn) throws Exception;

    List<Book> findBooksByAuthor(String author) throws Exception;

    void borrowBook(String isbn) throws Exception;

    void returnBook(String isbn) throws Exception;

}
