package lib.backend.libraryservice.Entity;

import java.util.Date;

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
@Table(name = "borrow")

public class Borrow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_code")
    private Integer book_code;

    @Column(name = "user_num")
    private Integer user_num;

    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

    public Borrow() {
    }

    public Borrow(Integer book_code, Integer user_num, Date start_date, Date end_date) {
        this.book_code = book_code;
        this.user_num = user_num;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}