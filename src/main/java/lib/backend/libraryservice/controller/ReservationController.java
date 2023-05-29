package lib.backend.libraryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import lib.backend.libraryservice.Entity.User;
import lib.backend.libraryservice.repository.ReservationRepository;
import lib.backend.libraryservice.service.ReservationService;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @ResponseBody
    @RequestMapping("/reservation")
    public String reservation(@RequestParam("code") String code, HttpSession session,
            Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            String message = "<script>alert('로그인하셔야 합니다.');history.back();</script>";
            return message;
        }
        int bookcode = Integer.parseInt(code); // book_code 값을 정수로 변환
        if (reservationService.checkBorrowedBooks(user.getUser_num()) > 5) { // 2.
            String message = "<script>alert('예약 권수를 초과했습니다.');history.back();</script>";
            return message;
        } else {
            // and message
            reservationService.reservationFunc(bookcode, user.getUser_num());
            String message = "<script>alert('예약되었습니다.');history.back();</script>";
            return message;
        }
        // 대출 여부 확인
        // 대출중일 경우, 예약 하기.
        // 예상 대출 가능일자(반납일 + 14 * '예약자 수')
    }
}