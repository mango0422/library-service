package lib.backend.libraryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lib.backend.libraryservice.Entity.*;
import lib.backend.libraryservice.service.*;

@Controller
public class adminController {

    private BorrowService borrowService;
    private BookService bookService;
    private ReservationService reservationService;

    @Autowired
    public adminController(BorrowService borrowService, BookService bookService,
            ReservationService reservationService) {
        this.borrowService = borrowService;
        this.bookService = bookService;
        this.reservationService = reservationService;
    }

    @GetMapping("/managePage")
    public String managePage() {
        List<Book> books = bookService.selectBooks();
        List<Borrow> not_auths = borrowService.listNotAuth();
        List<Reservation> waitings = reservationService.getWaitingList();
        return "managePage";
    }
}
