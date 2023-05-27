package lib.backend.libraryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lib.backend.libraryservice.Entity.Reservation;;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 예약자 수 counting하는 메서드
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.book_code = :book_code")
    Integer countReservationByBookCode(@Param("book_code") Integer bookCode);

    // 본인이 예약한 도서 목록 조회하는 메서드
    // 예약자 수 counting하는 메서드
    @Query("SELECT r FROM Reservation r WHERE r.user_num = :user_num")
    List<Reservation> getListOfReservations(@Param("user_num") Integer userNum);

    @Query("INSERT INTO Reservation (book_code, user_num, date) VALUES (:book_code, :user_num, :date)")
    void bookReservationInsert(@Param("book_code") Integer bookCode, @Param("user_num") Integer userNum,
            @Param("date") String date);
}