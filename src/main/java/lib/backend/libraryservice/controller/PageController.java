package lib.backend.libraryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lib.backend.libraryservice.Entity.User;
import lib.backend.libraryservice.service.BorrowService;
import lib.backend.libraryservice.service.ReservationService;
import lib.backend.libraryservice.Entity.Borrow;
import lib.backend.libraryservice.Entity.Reservation;

@Controller
public class PageController {

    private BorrowService borrowService;
    private ReservationService reservationService;

    @Autowired
    public PageController(ReservationService reservationService, BorrowService borrowService) {
        this.borrowService = borrowService;
        this.reservationService = reservationService;
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            List<Borrow> borrows = borrowService.listBorrowByUserNum(user.getUser_num());
            List<Reservation> reservations = reservationService.getListReservation(user.getUser_num());
            model.addAttribute("borrows", borrows);
            model.addAttribute("reservations", reservations);
            return "mypage";
        } else {
            return "/home";
        }
    }

    @GetMapping("/introduce")
    public String introduce(HttpServletRequest request, Model model) {
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        return "introduce";
    }

    // @GetMapping("/community")
    // public String community() {
    // return "community";
    // }

    @GetMapping("/community")
    public String community() {
        return "Community";
    }
}