package lib.backend.libraryservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lib.backend.libraryservice.Entity.Borrow;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    @Query("SELECT b.end_date FROM Borrow b WHERE b.book_code = :book_code AND b.user_num = :user_num")
    Date getEndDateByUserNumAndBookCode(@Param("book_code") Integer book_code, @Param("user_num") Integer user_num);

    @Query("SELECT b FROM Borrow b WHERE b.user_num = :user_num")
    List<Borrow> getListOfBorrows(@Param("user_num") Integer user_num);

    @Query("SELECT b.user_num FROM Borrow b WHERE b.book_code =:book_code")
    Integer showBorrowUserNum(@Param("book_code") Integer book_code);

    @Query("SELECT count(b) FROM Borrow b WHERE b.user_num =:user_num")
    Integer checkBorrowedBooks(@Param("user_num") Integer user_num);

    @Modifying
    @Query(value = "INSERT INTO borrow (book_code, user_num) values (:book_code, :user_num)", nativeQuery = true)
    void doBorrow(@Param("book_code") Integer book_code, @Param("user_num") Integer user_num);

    @Modifying
    @Query("DELETE FROM borrow b WHERE b.book_code = :book_code")
    void endBorrow(@Param("book_code") Integer book_code);
}