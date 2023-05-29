package lib.backend.libraryservice.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jakarta.persistence.Query;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import lib.backend.libraryservice.Entity.Book;
import lib.backend.libraryservice.Entity.Borrow;
import lib.backend.libraryservice.Entity.User;
import lib.backend.libraryservice.repository.BorrowRepository;
import lib.backend.libraryservice.repository.BookRepository;
import lib.backend.libraryservice.service.BookService;

@Service
public class BorrowService {

    private JdbcTemplate jdbcTemplate;
    private BorrowRepository borrowRepository;
    private BookRepository bookRepository;
    private ReservationService reservationService;

    @Autowired
    public BorrowService(JdbcTemplate jdbcTemplate, BorrowRepository borrowRepository, BookRepository bookRepository,
            ReservationService reservationService) {
        this.jdbcTemplate = jdbcTemplate;
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.reservationService = reservationService;
    }

    @Autowired
    private EntityManager entityManager;

    public List<Book> executeQuery(String query) {
        Query nativeQuery = entityManager.createNativeQuery(query, Borrow.class);
        List<Book> bookborrows = nativeQuery.getResultList();
        return bookborrows;
    }

    // 유저 코드를 이용하여 예약한 도서 목록 출력하기.
    public List<Book> getListBooks(Integer user_num) {
        List<Integer> bookCodes = borrowRepository.findByUser_num(user_num);
        List<Book> books = bookRepository.findBooksByBookCode(bookCodes);
        return books;
    }

    public List<Borrow> getListBorrow(Integer user_num) {
        List<Borrow> bookCodes = borrowRepository.findByUser_num2(user_num);
        return bookCodes;
    }

    // 대출 기능
    @Transactional
    public void borrowFunc(Integer user_num, Integer code) {
        Book book = bookRepository.getByBookCode(code);
        borrowRepository.doBorrow(code, book.getTitle(), book.getAuthor(), book.getISBN(), user_num);
        bookRepository.updateBookBorrowByBookCode(code, user_num);
    }

    // 대출 여부 확인
    public Integer checkBorrow(Integer book_code) {
        return bookRepository.bookBorrowedUserNum(book_code);
    }

    public Integer checkBorrow2(Integer book_code, Integer user_num) {
        return borrowRepository.bookBorrowedUserNumCode(book_code, user_num);
    }

    // 반납 기능
    public void returnFunc(Integer book_code, Integer user_num) {
        String sql = "DELETE FROM borrow WHERE book_code = ?";
        jdbcTemplate.update(sql, book_code);
        bookRepository.updateBookBorrowByBookCode(book_code, null);
    }

    // 살재 대출 구현
    public String borrowFinal(Integer book_code, Integer user_num) {
        if (checkBorrow(book_code) == 0) {
            borrowFunc(user_num, book_code);
            return "borrowed"; // 대출 성공 시 "borrowed"를 리턴
        } else {
            return "already_borrowed"; // 이미 대출 중인 경우 "already_borrowed"를 리턴
        }
    }

    // 대출한 사람 고유번호 출력
    public Integer showBorrowUserNum(Integer book_code) {
        return borrowRepository.showBorrowUserNum(book_code);
    }

    // 대출 버튼 표시 여부 판단
    public Integer borrowBtnCondition(Integer book_code, Integer user_num) {
        Integer num = 0;
        if (checkBorrow(book_code) == 0) { // 대출 여부 확인
            num = 0;
        } else if (checkBorrow2(book_code, user_num) == 0) {
            num = 1;
        } else if (reservationService.checkReservation(user_num, book_code) == 0) {
            num = 2;
        } else if (reservationService.checkReservation(user_num, book_code) == 1) {
            num = 3;
        }
        return num;
    }

    public Integer borrowBtnCondition2(Integer book_code) {
        if (checkBorrow(book_code) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public Integer checkBorrowedBooks(Integer user_num) {
        return borrowRepository.checkBorrowedBooks(user_num);
    }

    public Integer ifExistsByCode(Integer code) {
        return borrowRepository.ifExistsByCode(code);
    }

    public List<Borrow> listBorrowByUserNum(Integer user_num) {
        return borrowRepository.listBorrowByUserNum(user_num);
    }

    public List<Borrow> listNotAuth() {
        return borrowRepository.listNotAuth();
    }

    public void updateAuthStatus(Integer code) {
        borrowRepository.updateAuthStatus(code);
    }
}