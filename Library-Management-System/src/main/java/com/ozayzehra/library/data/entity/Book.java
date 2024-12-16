package com.ozayzehra.library.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", length = 255, nullable = false, unique = true)
    private String isbn;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "author", length = 255, nullable = false)
    private String author;

    @Column(name = "publicationYear", nullable = false)
    private Integer publicationYear;

    @Column(name = "availableCopies", nullable = false)
    private Integer availableCopies;

    public Book() {
;
    }
    public Book(String isbn, String title, String author, Integer publicationYear, Integer availableCopies) {
        this.setIsbn(isbn);
        this.setTitle(title);
        this.setAuthor(author);
        this.setAvailableCopies(availableCopies);
        this.setPublicationYear(publicationYear);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
