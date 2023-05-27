package lib.backend.libraryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lib.backend.libraryservice.Entity.Book;
import lib.backend.libraryservice.repository.BookRepository;
import java.util.List;

@Service
public class BookService {
    public List<Book> searchBooks(
            String title1, String title2, String title3,
            String input1, String input2, String input3,
            String operator1, String operator2,
            String book_type, String book_lang,
            Integer start_year, Integer end_year) {
        // // ... 쿼리 조건을 생성하는 로직 추가 ...

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM book WHERE ");
        if ((input1 == null || !input1.isEmpty()) && (input2 == null || input2.isEmpty())
                && (input3 == null && input3.isEmpty())) {
            return null;
        }
        if (title1 != null && !title1.isEmpty() && input1 != null && !input1.isEmpty()) {
            queryBuilder.append(title1).append(" LIKE '%").append(input1).append("%'");
        }
        if (title2 != null && !title2.isEmpty() && input2 != null && !input2.isEmpty()) {
            if (operator1 != null && !operator1.isEmpty() && input1 != null && !input1.isEmpty()) {
                if (operator1.equals("AND") || operator1.equals("OR")) {
                    queryBuilder.append(operator1).append(" ").append(title2);
                } else if (operator1.equals("NOT")) {
                    queryBuilder.append(" AND ").append(title2).append(" NOT");
                }
                queryBuilder.append(" LIKE '%").append(input2).append("%'");
            } else {
                queryBuilder.append(title2).append(" LIKE '%").append(input2).append("%'");
            }
        }
        if (title3 != null && !title3.isEmpty() && input3 != null && !input3.isEmpty()) {
            if (operator2 != null && !operator2.isEmpty()
                    && ((input1 != null && !input1.isEmpty()) || input2 != null && !input2.isEmpty())) {
                if (operator2.equals("AND") || operator2.equals("OR")) {
                    queryBuilder.append(operator2).append(" ").append(title3);
                } else if (operator1.equals("NOT")) {
                    queryBuilder.append(" AND ").append(title3).append(" NOT");
                }
                queryBuilder.append(" LIKE '%").append(input3).append("%'");
            } else {
                queryBuilder.append(title3).append(" LIKE '%").append(input3).append("%'");
            }
        }
        if (!book_type.equals("all")) {
            queryBuilder.append(" AND book_type = '").append(book_type).append("'");
        }
        if (!book_lang.equals("all")) {
            queryBuilder.append(" AND book_lang = '").append(book_lang).append("'");
        }
        if (start_year != 0) {
            queryBuilder.append(" AND book_year >= ").append(start_year);
        }
        if (end_year != 0) {
            queryBuilder.append(" AND book_year <= ").append(end_year);
        }
        String query = queryBuilder.toString();

        // 레포지토리를 사용하여 사용자 정의 쿼리 실행 및 검색 결과 가져오기
        List<Book> searchResults = executeQuery(query);

        return searchResults;
    }

    @Autowired
    private EntityManager entityManager;

    public List<Book> executeQuery(String query) {
        Query nativeQuery = entityManager.createNativeQuery(query, Book.class);
        List<Book> books = nativeQuery.getResultList();
        return books;
    }

    private BookRepository bookRepository;

    // book_code를 조건으로 Book 데이터 조회하기
    public Book getByBookCode(Integer book_code) {
        return bookRepository.getByBookCode(book_code);
    }
}