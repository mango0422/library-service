package lib.backend.libraryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lib.backend.libraryservice.Entity.User;
import lib.backend.libraryservice.repository.BorrowRepository;
import lib.backend.libraryservice.service.BorrowService;

@Controller
public class BorrowController {

    @Autowired
    private BorrowService borrowService;
    private BorrowRepository borrowRepository;

    // @PostMapping("/borrow")
    // public String borrow(@RequestParam("book_code") String bookcode, HttpSession
    // session, Model model) {
    // User user = (User) session.getAttribute("user");
    // if (user == null) { // 1. 로그인 안한경우
    // return "1";
    // }
    // // bookId를 가져와서 BookCode로 책 정보 조회
    // int book_code = 0;
    // try {
    // book_code = Integer.parseInt(bookcode);
    // } catch (NumberFormatException e) {

    // }
    // // 대출 버튼 누르면 타임스탬프 찍고,
    // if (borrowService.checkBorrowedBooks(user.getUser_num()) > 5) { // 2.
    // return "2";
    // } else {
    // borrowService.borrowFunc(book_code, user.getUser_num());
    // borrowService.borrowFunc(user.getUser_num(), book_code);
    // return "3";
    // }
    // }

    @PostMapping("/borrow")
    public ResponseEntity<String> borrow(@RequestParam("book_code") String book_code, HttpSession session,
            Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) { // 1. 로그인 안한경우
            return ResponseEntity.ok("1|로그인하셔야 합니다."); // Return response with status code "1" and message
        }
        // bookId를 가져와서 BookCode로 책 정보 조회
        // int book_code = 0;
        // book_code = Integer.parseInt(bookcode);
        // 대출 버튼 누르면 타임스탬프 찍고,
        int bookcode = Integer.parseInt(book_code); // book_code 값을 정수로 변환
        if (borrowService.checkBorrowedBooks(user.getUser_num()) > 5) { // 2.
            return ResponseEntity.ok("2|대출 권수를 초과했습니다."); // Return response with status code "2" and message
        } else {
            borrowService.borrowFunc(user.getUser_num(), bookcode);
            return ResponseEntity.ok("3|대출되었습니다. 3시간 내에 대출 완료를 하셔야 대출이 완료됩니다."); // Return response with status code "3"
            // and message
        }
    }
}