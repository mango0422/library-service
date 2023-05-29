package lib.backend.libraryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

// import com.google.type.Date;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    @ResponseBody
    @PostMapping(value = "/signup")
    public String signup(
            @RequestParam("user_id") String user_id,
            @RequestParam("user_pw") String user_pw,
            @RequestParam("user_pw2") String user_pw2,
            @RequestParam("user_name") String user_name,
            @RequestParam("email") String email,
            @RequestParam("birthdate") String birthdate) {
        if (!user_pw.equals(user_pw2)) {
            String message = "<script>alert('비밀번호가 일치하지 않습니다. 다시한번 확인해주세요');location.href='/signup';</script>";
            return message;
        }

        User userId = userRepository.findByUserId(user_id);
        User userEmail = userRepository.findByEmail(email);
        if (userId != null) {
            String message = "<script>alert('이미 존재하는 아이디입니다.');location.href='/signup';</script>";
            return message;
        } else if (userEmail != null) {
            String message = "<script>alert('이미 존재하는 이메일입니다.');location.href='/signup';</script>";
            return message;
        }

        if (user_id.isEmpty() || user_pw.isEmpty() || user_pw2.isEmpty() || user_name.isEmpty() || email.isEmpty()
                || birthdate.isEmpty()) {
            String message = "<script>alert('모든 필드를 입력해주세요.');location.href='/signup';</script>";
            return message;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
            String message = "<script>alert('유효하지 않은 생년월일 형식입니다.');location.href='/signup';</script>";
            return message;
        }
        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

        User user = new User();
        user.setUser_id(user_id);
        user.setUser_pw(user_pw);
        user.setUser_name(user_name);
        user.setEmail(email);
        user.setBirthdate(sqlDate);
        userRepository.save(user);

        String message = "<script>alert('회원가입되셨습니다.');location.href='/home';</script>";
        return message;
    }
}