package lib.backend.libraryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class MyScheduler {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MyScheduler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(fixedRate = 60000) // 1분마다 실행
    public void deleteOldData() {
        LocalDateTime currentDateTime = LocalDateTime.now();

        String deleteQuery = "DELETE FROM reservation WHERE date < ?";
        jdbcTemplate.update(deleteQuery, currentDateTime.minusHours(3));
    }
}