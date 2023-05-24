package lib.backend.libraryservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lib.backend.libraryservice.Entity.User;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        // 세션에서 로그인한 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");
        if (user != null) {
            // 로그인한 사용자일 경우 홈 페이지 보여주기
            model.addAttribute("user", user);
            return "logined";
        } else {
            return "home";
        }
    }

    @GetMapping("/")
    public String blank() {
        return "redirect:/home";
    }
}