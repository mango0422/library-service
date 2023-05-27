package lib.backend.libraryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import lib.backend.libraryservice.repository.ReservationRepository;
import lib.backend.libraryservice.service.ReservationService;

@Controller
public class ReservationController {
    @PostMapping("/reservation")
    public String reservation(HttpSession httpSession, Model model) {
        // 대출 여부 확인
        // 대출중일 경우, 예약 하기.
        // 예상 대출 가능일자(반납일 + 14 * '예약자 수')
        return "redirect:popup/reservation";
    }
}