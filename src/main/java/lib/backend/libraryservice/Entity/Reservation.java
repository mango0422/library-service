package lib.backend.libraryservice.Entity;

import com.google.cloud.Date;

// import java.sql.Date;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "reservation")

public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_code")
    private Integer book_code;

    @Column(name = "user_num")
    private Integer user_num;

    @Column(name = "start_date")
    private Timestamp date;

    public Reservation() {
    }

    public Reservation(Integer book_code, Integer user_num, Timestamp date) {
        this.book_code = book_code;
        this.user_num = user_num;
        this.date = date;
    }
}