package lib.backend.libraryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpSession;
import lib.backend.libraryservice.Entity.User;

@Controller
public class PageController implements WebMvcConfigurer {

    // @Autowired
    // private MypageService MypageService;

    @GetMapping("/mypage")
    public String mypage(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
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
