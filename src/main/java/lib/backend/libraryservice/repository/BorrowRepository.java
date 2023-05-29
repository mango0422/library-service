package lib.backend.libraryservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lib.backend.libraryservice.Entity.Borrow;
import lib.backend.libraryservice.Entity.Book;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    @Query("SELECT b.end_date FROM Borrow b WHERE b.code = :code AND b.user_num = :user_num")
    Date getEndDateByUserNumAndBookCode(@Param("code") Integer code, @Param("user_num") Integer user_num);

    @Query("SELECT b FROM Borrow b WHERE b.user_num = :user_num")
    List<Borrow> getListOfBorrows(@Param("user_num") Integer user_num);

    @Query("SELECT b.user_num FROM Borrow b WHERE b.code =:code")
    Integer showBorrowUserNum(@Param("code") Integer code);

    @Query("SELECT count(b) FROM Borrow b WHERE b.user_num =:user_num")
    Integer checkBorrowedBooks(@Param("user_num") Integer user_num);

    @Modifying
    @Query(value = "INSERT INTO borrow (code, title, author, ISBN, user_num) values (:code, :title, :author, :ISBN, :user_num)", nativeQuery = true)
    void doBorrow(@Param("code") Integer code, @Param("title") String title, @Param("author") String author,
            @Param("ISBN") String ISBN, @Param("user_num") Integer user_num);

    @Query(value = "SELECT code FROM borrow WHERE user_num = ?1", nativeQuery = true)
    List<Integer> findByUser_num(int user_num);

    @Query(value = "SELECT * FROM borrow WHERE user_num = ?1", nativeQuery = true)
    List<Borrow> findByUser_num2(int user_num);

    @Query(value = "SELECT count(*) FROM borrow WHERE code = ?1", nativeQuery = true)
    Integer ifExistsByCode(int code);

    @Query(value = "SELECT count(*) FROM borrow WHERE code = ?1 AND user_num = ?2", nativeQuery = true)
    Integer bookBorrowedUserNumCode(int code, int user_num);

    @Query(value = "SELECT * FROM borrow WHERE user_num = ?1", nativeQuery = true)
    List<Borrow> listBorrowByUserNum(int user_num);

    @Query(value = "SELECT * FROM borrow WHERE auth_stat = 0", nativeQuery = true)
    List<Borrow> listNotAuth();

    @Modifying
    @Query(value = "UPDATE Borrow SET auth_stat = 1 WHERE code = ?1", nativeQuery = true)
    void updateAuthStatus(Integer code);
}
/*
 * 예약 조건
 * 
 * 1. session의 user!=null 일경우
 * 2. 대출중일 경우.
 */