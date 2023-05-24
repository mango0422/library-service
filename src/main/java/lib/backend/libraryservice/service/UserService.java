package lib.backend.libraryservice.service;

// import com.google.cloud.Date;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lib.backend.libraryservice.Entity.User;
import lib.backend.libraryservice.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // public boolean authenticateUser(String user_id, String user_pw) {
    // User user = userRepository.findByUserIdAndUserPw(user_id, user_pw);
    // return user != null;
    // }

    // user_id와 user_pw DB에서 검색해서 반환하기
    public User findByUserIdAndPassword(String user_id, String user_pw) {
        return userRepository.findByUserIdAndUserPw(user_id, user_pw);
    }

    // user_id DB에서 검색해서 반환하기
    public User findByUserId(String user_id) {
        return userRepository.findByUserId(user_id);
    }

    // email DB에서 검색해서 반환하기
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // user_id 중복 유무를 DB에서 검색해서 참 거짓으로 반환하기
    public boolean isUserExists(String id) {
        User user = userRepository.findByUserId(id);
        return user != null;
    }

    // email 중복 유무를 DB에서 검색해서 참 거짓으로 반환하기
    public boolean isEmailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}