package lib.backend.libraryservice.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lib.backend.libraryservice.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // User findByUserIdAndUserPw(String user_id, String user_pw);
    @Query("SELECT u FROM User u WHERE u.user_id = :user_id AND u.user_pw = :user_pw")
    User findByUserIdAndUserPw(@Param("user_id") String user_id, @Param("user_pw") String user_pw);

    @Query("SELECT u FROM User u WHERE u.user_id = :user_id")
    User findByUserId(@Param("user_id") String user_id);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    // @Query("INSERT INTO user (user_id, user_pw, user_name, email, birthdate)
    // VALUES (:userId, :userPw, :user_name, :email, :birthdate)")
    // User saveUser(@Param("user_id") String user_id, @Param("uesr_pw") String
    // user_pw,
    // @Param("user_name") String user_name, @Param("email") String email,
    // @Param("birthdate") Date bitrhdate);
}