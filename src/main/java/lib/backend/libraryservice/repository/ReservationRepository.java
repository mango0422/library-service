package lib.backend.libraryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lib.backend.libraryservice.Entity.Reservation;;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 예약자 수 counting하는 메서드
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.code = :code")
    Integer countReservationByBookCode(@Param("code") Integer bookCode);

    // 본인이 예약한 도서 목록 조회하는 메서드
    // 예약자 수 counting하는 메서드
    @Query("SELECT r FROM Reservation r WHERE r.user_num = :user_num")
    List<Reservation> getListOfReservations(@Param("user_num") Integer userNum);

    @Modifying
    @Query("INSERT INTO Reservation (code, user_num, title, author, ISBN, expecteddate) VALUES (:code, :user_num, :title, :author, :ISBN,:expecteddate)")
    void bookReservationInsert(@Param("code") Integer bookCode, @Param("user_num") Integer userNum,
            @Param("title") String title,
            @Param("author") String author, @Param("ISBN") String ISBN, @Param("expecteddate") String expecteddate);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.user_num =:user_num")
    Integer checkReservationBooks(@Param("user_num") Integer user_num);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.user_num =:user_num AND r.code = :code")
    Integer checkReservation(@Param("user_num") Integer user_num, @Param("code") Integer code);

    @Query(value = "SELECT * FROM reservation WHERE user_num = ?1", nativeQuery = true)
    List<Reservation> getListReservation(Integer user_num);
}