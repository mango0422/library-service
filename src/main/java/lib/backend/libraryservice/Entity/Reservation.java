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
    @Column(name = "code")
    private Integer code;

    @Column(name = "user_num")
    private Integer user_num;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "expecteddate")
    private Timestamp expecteddate;

    public Reservation() {
    }

    public Reservation(Integer code, Integer user_num, String title, String author, String ISBN,
            Timestamp expecteddate) {
        this.code = code;
        this.user_num = user_num;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.expecteddate = expecteddate;
    }
}