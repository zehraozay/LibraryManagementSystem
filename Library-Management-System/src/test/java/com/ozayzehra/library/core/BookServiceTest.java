package com.ozayzehra.library.core;

import com.ozayzehra.library.core.dto.BookDTO;
import com.ozayzehra.library.core.exception.NotFoundException;
import com.ozayzehra.library.data.dao.BookDAO;
import com.ozayzehra.library.data.entity.Book;
import com.ozayzehra.library.core.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookDAO bookDAO;

    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setId(1L);
        book.setIsbn("TestISBN5");
        book.setTitle("TestBook5");
        book.setAuthor("TestAuthor5");
        book.setPublicationYear(2022);
        book.setAvailableCopies(5);

        bookDTO = new BookDTO();
        bookDTO.setIsbn("TestISBN5");
        bookDTO.setTitle("TestBook5");
        bookDTO.setAuthor("TestAuthor5");
        bookDTO.setPublicationYear(2022);
        bookDTO.setAvailableCopies(5);

    }

    @Test
    public void testAddBook() throws Exception {

        when(bookDAO.save(any(Book.class))).thenReturn(book);
        bookService.addBook(bookDTO);

        verify(bookDAO, times(1)).save(any(Book.class));
    }

    @Test
    public void testRemoveBook() throws Exception {
        when(bookDAO.findBookByISBN("TestISBN5")).thenReturn(book);
        doNothing().when(bookDAO).deleteById(anyLong());
        bookService.removeBook("TestISBN5");

        verify(bookDAO, times(1)).deleteById(anyLong());
        verify(bookDAO, times(1)).findBookByISBN("TestISBN5");
    }

    @Test
    public void testFindBookByISBN_whenCached() throws Exception {

        bookService.addBook(bookDTO);
        Book book = bookService.findBookByISBN("TestISBN5");

        assertNotNull(book);
        assertEquals("TestISBN5", book.getIsbn());
    }

    @Test
    public void testFindBookByISBN_whenNotCached() throws Exception {
        when(bookDAO.findBookByISBN("TestISBN5")).thenReturn(book);
        Book book = bookService.findBookByISBN("TestISBN5");

        assertNotNull(book);
        assertEquals("TestISBN5", book.getIsbn());
    }

    @Test
    public void testFindBooksByAuthor() throws Exception {
        when(bookDAO.findBooksByAuthor("TestBook5")).thenReturn(Arrays.asList(book));
        List<Book> books = bookService.findBooksByAuthor("TestBook5");

        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals("TestAuthor5", books.get(0).getAuthor());
    }


    @Test
    public void testBorrowBook_whenBookAvailable() throws Exception {
        when(bookDAO.findBookByISBN("TestISBN5")).thenReturn(book);
        when(bookDAO.save(any(Book.class))).thenReturn(book);
        bookService.borrowBook("TestISBN5");

        assertEquals(4, book.getAvailableCopies());
        verify(bookDAO, times(1)).save(any(Book.class));
    }

    @Test
    public void testBorrowBook_NoCopiesAvailable() throws Exception {
        book.setAvailableCopies(0);
        when(bookDAO.findBookByISBN("TestISBN5")).thenReturn(book);
        bookService.borrowBook("TestISBN5");

        assertEquals(0, book.getAvailableCopies());
        verify(bookDAO, times(0)).save(any(Book.class));
    }

    @Test
    public void testReturnBook() throws Exception {
        when(bookDAO.findBookByISBN("TestISBN5")).thenReturn(book);
        when(bookDAO.save(any(Book.class))).thenReturn(book);
        bookService.returnBook("TestISBN5");

        assertEquals(6, book.getAvailableCopies());
        verify(bookDAO, times(1)).save(any(Book.class));
    }
}
