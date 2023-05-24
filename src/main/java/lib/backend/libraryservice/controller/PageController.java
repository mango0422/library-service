package lib.backend.libraryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import lib.backend.libraryservice.Entity.User;
import lib.backend.libraryservice.service.BorrowService;
import lib.backend.libraryservice.Entity.Borrow;

@Controller
public class PageController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/mypage")
    public String mypage(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            List<Borrow> borrows = borrowService.findByID(user.getUser_num());
            model.addAttribute("borrows", borrows);
            return "mypage";
        } else {
            return "/home";
        }
    }

    @GetMapping("/introduce")
    public String introduce() {
        return "introduce";
    }

    @GetMapping("/community")
    public String community() {
        return "community";
    }
}