package lib.backend.libraryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lib.backend.libraryservice.Entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.book_code = :book_code")
    Book getByBookCode(@Param("book_code") Integer book_code);

    @Query("SELECT b.book_borrow FROM Book b WHERE b.book_code = :book_code")
    Integer bookBorrow(@Param("book_code") Integer book_code);

    @Modifying
    @Query("UPDATE Book b SET b.book_borrow = :book_borrow WHERE b.book_code = :book_code")
    void updateBookBorrowByBookCode(@Param("book_code") int book_code, @Param("book_borrow") Integer book_borrow);

    @Query(value = "SELECT * FROM book LEFT JOIN borrow ON book.book_code = borrow.book_code WHERE borrow.user_num = ?1", nativeQuery = true)
    List<Book> findBookWithBorrowByUserNum(int user_num);
}