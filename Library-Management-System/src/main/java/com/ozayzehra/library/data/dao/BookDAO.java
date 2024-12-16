package com.ozayzehra.library.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import com.ozayzehra.library.data.entity.Book;


public interface BookDAO extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn")
    public Book findBookByISBN(String isbn);

    @Query("SELECT b FROM Book b WHERE b.author = :author")
    public List<Book> findBooksByAuthor(String author);

}
