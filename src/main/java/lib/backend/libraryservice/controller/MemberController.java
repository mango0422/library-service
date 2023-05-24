package lib.backend.libraryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lib.backend.libraryservice.Entity.User;
import lib.backend.libraryservice.service.UserService;

@Controller
public class MemberController implements WebMvcConfigurer {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam String user_id, @RequestParam String user_pw,
            HttpSession session, HttpServletRequest request) {
        User user_login_check = userService.findByUserIdAndPassword(user_id, user_pw);
        if (user_login_check == null) {
            model.addAttribute("error", "Invalid user_id or user_pw.");
            return "/home";
        }
        User user = userService.findByUserId(user_id);

        // Set the user object in the session.
        session.setAttribute("user", user);

        System.out.println("Session after setting attribute: " + session.getAttribute("user"));
        // 로그인 성공 시
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 초기화
        return "redirect:/home"; // 로그인 페이지로 리다이렉트
    }
}