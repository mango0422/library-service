package lib.backend.libraryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import lib.backend.libraryservice.Entity.User;
import lib.backend.libraryservice.repository.BorrowRepository;
import lib.backend.libraryservice.service.BorrowService;

@Controller
public class BorrowController {

    private BorrowService borrowService;
    private BorrowRepository borrowRepository;

    @Autowired
    public BorrowController(BorrowService borrowService, BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
        this.borrowService = borrowService;
    }

    @ResponseBody
    @RequestMapping("/borrow")
    public String borrow(@RequestParam("code") String code, HttpSession session,
            Model model) {
        int bookcode = Integer.parseInt(code); // book_code 값을 정수로 변환
        if (borrowService.ifExistsByCode(bookcode) != 0) {
            String message = "<script>alert('이미 누군가 대출하였습니다.');history.back();</script>";
            return message;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) { // 1. 로그인 안한경우
            String message = "<script>alert('로그인하셔야 합니다.');history.back();</script>";
            return message;
        }
        // bookId를 가져와서 BookCode로 책 정보 조회
        // int book_code = 0;
        // book_code = Integer.parseInt(bookcode);
        // 대출 버튼 누르면 타임스탬프 찍고,
        if (borrowService.checkBorrowedBooks(user.getUser_num()) > 5) { // 2.
            String message = "<script>alert('대출 권수를 초과했습니다.');history.back();</script>";
            return message;
        } else {
            borrowService.borrowFunc(user.getUser_num(), bookcode);
            String message = "<script>alert('대출되었습니다. 3시간 내에 대출 완료를 하셔야 대출이 완료됩니다.');history.back();</script>";
            return message; // Return response with status code "3"
            // and message
        }
    }

    @ResponseBody
    @RequestMapping("/return")
    public String returnBook(@RequestParam("code") String code, HttpSession session,
            Model model) {
        int bookcode = Integer.parseInt(code); // book_code 값을 정수로 변환
        if (borrowService.ifExistsByCode(bookcode) == 0) {
            String message = "<script>alert('이미 누군가 반납하였습니다.');history.back();</script>";
            return message;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) { // 1. 로그인 안한경우
            String message = "<script>alert('로그인하셔야 합니다.');history.back();</script>";
            return message;
        }
        if (user.getUser_num() == borrowService.showBorrowUserNum(bookcode)) {
            borrowService.returnFunc(bookcode, user.getUser_num());
            String message = "<script>alert('반납되었습니다.');location.href='/mypage';</script>";
            return message; // Return response with status code "3"
        } else {
            String message = "<script>alert('일시적인 에러입니다.');history.back();</script>";
            return message;
        }
        // and message
    }
}