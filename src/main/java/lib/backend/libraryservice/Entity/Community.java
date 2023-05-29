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
public class Community implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_num")
    private Integer board_num;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "writeDate")
    private Date writeDate;

    public Community() {

    }

    public Community(Integer board_num, String title, String contents, Date writeDate) {
        this.board_num = board_num;
        this.title = title;
        this.contents = contents;
        this.writeDate = writeDate;
    }
}
