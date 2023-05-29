package lib.backend.libraryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lib.backend.libraryservice.Entity.Book;
import lombok.Data;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE b.code = :code")
    Book getByBookCode(@Param("code") Integer book_code);

    @Query("SELECT b.borrow FROM Book b WHERE b.code = :code")
    Integer bookBorrowedUserNum(@Param("code") Integer book_code);

    @Modifying
    @Query("UPDATE Book b SET b.borrow = :borrow WHERE b.code = :code")
    void updateBookBorrowByBookCode(@Param("code") int code, @Param("borrow") Integer borrow);

    @Query(value = "SELECT * FROM book WHERE code = ?1", nativeQuery = true)
    List<Book> findBookWithBorrowByUserNum(int code);

    @Query(value = "SELECT * FROM book WHERE book_code IN ?1", nativeQuery = true)
    List<Book> findBooksByBookCode(List<Integer> bookCodes);
}