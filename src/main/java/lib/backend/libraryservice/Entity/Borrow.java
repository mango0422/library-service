package lib.backend.libraryservice.Entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "borrow")

public class Borrow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "user_num")
    private Integer user_num;

    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

    @Column(name = "extension_cnt")
    private Integer extension_cnt;

    @Column(name = "auth_stat")
    private Integer auth_stat;

    public Borrow() {
    }

    public Borrow(Integer code, Integer user_num, String title, String author, String ISBN, Date start_date,
            Date end_date, Integer extension_cnt, Integer auth_stat) {
        this.code = code;
        this.user_num = user_num;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.start_date = start_date;
        this.end_date = end_date;
        this.extension_cnt = extension_cnt;
        this.auth_stat = auth_stat;
    }
}