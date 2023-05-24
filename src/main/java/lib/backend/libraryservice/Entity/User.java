package lib.backend.libraryservice.Entity;

// import com.google.cloud.Date;

import java.sql.Date;

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
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_num")
    private Integer user_num;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "user_pw")
    private String user_pw;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "email")
    private String email;

    @Column(name = "birthdate")
    private Date birthdate;

    // 생성자
    public User() {
    }

    public User(String user_id, String user_pw, String user_name, String email, Date birthdate) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.email = email;
        this.birthdate = birthdate;
    }
}