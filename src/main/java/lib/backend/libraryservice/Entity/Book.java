package lib.backend.libraryservice.Entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "borrow")
    private Integer borrow;

    @Column(name = "lang")
    private String lang;

    @Column(name = "type")
    private String type;

    @Column(name = "year")
    private Integer year;

    @Column(name = "summary")
    private String summary;

    @Column(name = "ISBN")
    private String ISBN;

    // 생성자
    public Book() {
    }

    public Book(Integer code, String title, String author, String publisher, Integer borrow,
            String lang, String type, Integer year, String summary, String ISBN) {
        this.code = code;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.borrow = borrow;
        this.lang = lang;
        this.type = type;
        this.year = year;
        this.summary = summary;
        this.ISBN = ISBN;
    }
}