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
    @Column(name = "book_code")
    private Integer book_code;

    @Column(name = "book_title")
    private String book_title;

    @Column(name = "book_author")
    private String book_author;

    @Column(name = "book_publisher")
    private String book_publisher;

    @Column(name = "book_borrow")
    private Integer book_borrow;

    @Column(name = "book_lang")
    private String book_lang;

    @Column(name = "book_type")
    private String book_type;

    @Column(name = "book_year")
    private Integer book_year;

    @Column(name = "book_summary")
    private String book_summary;

    @Column(name = "book_ISBN")
    private String book_ISBN;

    // 생성자
    public Book() {
    }

    public Book(Integer book_code, String book_title, String book_author, String book_publisher, Integer book_borrow,
            String book_lang, String book_type, Integer book_year, String book_summary, String book_ISBN) {
        this.book_code = book_code;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.book_borrow = book_borrow;
        this.book_lang = book_lang;
        this.book_type = book_type;
        this.book_year = book_year;
        this.book_summary = book_summary;
        this.book_ISBN = book_ISBN;
    }
}