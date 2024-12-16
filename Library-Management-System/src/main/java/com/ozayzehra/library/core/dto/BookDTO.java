package com.ozayzehra.library.core.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class BookDTO {

    private Long id;
    private String isbn;
    private String title;
    private String author;
    private Integer publicationYear;
    private Integer availableCopies;


    public BookDTO() {
    }

    public BookDTO(String isbn, String title, String author, Integer publicationYear, Integer availableCopies) {
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
