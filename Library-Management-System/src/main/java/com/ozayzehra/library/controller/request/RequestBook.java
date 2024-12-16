package com.ozayzehra.library.controller.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestBook {

    private String isbn;
    private String title;
    private String author;
    private Integer publicationYear;
    private Integer availableCopies;

    public RequestBook(String isbn, String title, String author, Integer publicationYear, Integer availableCopies) {
        this.setIsbn(isbn);
        this.setTitle(title);
        this.setAuthor(author);
        this.setAvailableCopies(availableCopies);
        this.setPublicationYear(publicationYear);
}

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

}
