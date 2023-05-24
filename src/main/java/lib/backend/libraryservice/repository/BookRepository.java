package lib.backend.libraryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lib.backend.libraryservice.Entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.book_code = :book_code")
    Book getByBookCode(@Param("book_code") Integer book_code);
}