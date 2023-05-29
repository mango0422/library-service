package lib.backend.libraryservice.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import lib.backend.libraryservice.Entity.Book;
import lib.backend.libraryservice.Entity.Reservation;
import lib.backend.libraryservice.repository.BookRepository;
import lib.backend.libraryservice.repository.BorrowRepository;
import lib.backend.libraryservice.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    private BookRepository bookRepository;
    private BorrowRepository borrowRepository;

    public ReservationService(ReservationRepository reservationRepository, BookRepository bookRepository,
            BorrowRepository borrowRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
    }

    // 유저 고유번호로 예약한 리스트 꺼내기.
    public List<Reservation> findByID(Integer user_num) {
        return reservationRepository.getListOfReservations(user_num);
    }

    // 예약자 수 반환하는 메서드
    public Integer countReservations(Integer code) {
        return reservationRepository.countReservationByBookCode(code);
    }

    // 예약 기능 구현 메서드
    @Transactional
    public void reservationFunc(Integer code, Integer user_num) {
        Book book = bookRepository.getByBookCode(code);
        Date date1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String DateStr = sdf.format(date1);
        reservationRepository.bookReservationInsert(code, user_num, book.getTitle(), book.getAuthor(),
                book.getISBN(), DateStr);
    }

    // 예약 여부 판단 후 구현 메서드

    @Autowired
    private EntityManager entityManager;

    public List<Reservation> executeQuery(String query) {
        Query nativeQuery = entityManager.createNativeQuery(query, Reservation.class);
        List<Reservation> borrows = nativeQuery.getResultList();
        return borrows;
    }

    // 도서 예약 가능 예상 날짜
    public Date expectedBorrowDate(Integer code, Integer user_num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrowRepository.getEndDateByUserNumAndBookCode(code, user_num));
        calendar.add(Calendar.DATE, 14);
        Date updatedDate = calendar.getTime();
        return updatedDate;
    }

    public Integer checkBorrowedBooks(Integer user_num) {
        return reservationRepository.checkReservationBooks(user_num);
    }

    public Integer checkReservation(Integer user_num, Integer code) {
        if (reservationRepository.checkReservation(user_num, code) == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public List<Reservation> getListReservation(Integer user_num) {
        return reservationRepository.getListReservation(user_num);
    }

    public List<Reservation> getWaitingList(){
        return reservationRepository.findAll();
    }
}