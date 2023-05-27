package lib.backend.libraryservice.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Query;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.EntityManager;
import lib.backend.libraryservice.Entity.Borrow;
import lib.backend.libraryservice.Entity.User;
import lib.backend.libraryservice.repository.BorrowRepository;
import lib.backend.libraryservice.repository.BookRepository;
import lib.backend.libraryservice.service.BookService;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;
    private BookRepository bookRepository;

    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
    }

    @Autowired
    private EntityManager entityManager;

    public List<Borrow> executeQuery(String query) {
        Query nativeQuery = entityManager.createNativeQuery(query, Borrow.class);
        List<Borrow> borrows = nativeQuery.getResultList();
        return borrows;
    }

    // 유저 코드를 이용하여 에약한 도서 목록 출력하기.
    public List<Borrow> findByID(Integer user_num) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM borrow WHERE user_num=");
        queryBuilder.append(user_num);
        String query = queryBuilder.toString();

        List<Borrow> borrowLists = executeQuery(query);

        return borrowLists;
    }

    // 도서 예약 가능 예상 날짜
    public Date expectedLoanDate(Integer book_code, Integer user_num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrowRepository.getEndDateByUserNumAndBookCode(book_code, user_num));
        calendar.add(Calendar.DATE, 14);
        Date updatedDate = calendar.getTime();
        return updatedDate;
    }

    // 대출 기능
    public void borrowFunc(Integer user_num, Integer book_code) {
        Date date1 = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        Date date2 = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = sdf.format(date1);
        String endDateStr = sdf.format(date2);
        borrowRepository.bookBorrowInsert(book_code, user_num, startDateStr, endDateStr);
    }

    // 대출 여부 확인
    public Integer checkBorrow(Integer book_code) {
        return bookRepository.bookBorrow(book_code);
    }

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
    public Integer borrowBtnCondition(Integer book_code, HttpSession session) {
        if (checkBorrow(book_code) == 0) { // 대출 여부 확인
            return 1;
        }
        return 0;
    }

    public Integer checkBorrowedBooks(Integer user_num) {
        return borrowRepository.checkBorrowedBooks(user_num);
    }
}