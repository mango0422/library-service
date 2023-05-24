package lib.backend.libraryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// import com.google.type.Date;

import java.sql.Date;

import lib.backend.libraryservice.Entity.User;
import lib.backend.libraryservice.service.UserService;
import lib.backend.libraryservice.repository.UserRepository;

@Controller
public class SignUpController {

    @Autowired
    private UserRepository userRepository;

    // 회원가입 페이지로 넘어가는 기능
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(
            @RequestParam("user_id") String user_id,
            @RequestParam("user_pw") String user_pw,
            @RequestParam("user_pw2") String user_pw2,
            @RequestParam("user_name") String user_name,
            @RequestParam("email") String email,
            @RequestParam("birthdate") Date birthdate) {
        if (user_pw == user_pw2) {
            return "signup";
        }

        User userId = userRepository.findByUserId(user_id);
        User userEmail = userRepository.findByEmail(email);
        if (userId != null || userEmail != null) {
            // 이미 존재하는 사용자 혹은 이메일입니다.
            return "signup";
        }
        if (user_id == null || user_pw == null || user_pw2 == null || user_name == null || email == null
                || birthdate == null) {
            return "signup";
        }

        User user = new User();
        user.setUser_id(user_id);
        user.setUser_pw(user_pw);
        user.setUser_name(user_name);
        user.setEmail(email);
        user.setBirthdate(birthdate);
        userRepository.save(user);

        return "redirect:/";
    }
}