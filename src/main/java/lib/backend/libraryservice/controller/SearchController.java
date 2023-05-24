package lib.backend.libraryservice.controller;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lib.backend.libraryservice.Entity.Book;
import lib.backend.libraryservice.repository.BookRepository;
import lib.backend.libraryservice.service.BookService;

@Controller
public class SearchController {

    BookRepository bookRepository;
    BookService bookService;

    public SearchController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;

    }

    @GetMapping("/search-page")
    public String LibrarySearchPage() {
        return "LibrarySearchPage";
    }

    @PostMapping("/search")
    public String searchBooks(
            @RequestParam("title1") String title1,
            @RequestParam("title2") String title2,
            @RequestParam("title3") String title3,
            @RequestParam("input1") String input1,
            @RequestParam("input2") String input2,
            @RequestParam("input3") String input3,
            @RequestParam("operator1") String operator1,
            @RequestParam("operator2") String operator2,
            @RequestParam("book_type") String book_type,
            @RequestParam("book_lang") String book_lang,
            @RequestParam("start_year") Integer start_year,
            @RequestParam("end_year") Integer end_year,
            Model model) {

        // BookService를 사용하여 책 검색
        List<Book> books = bookService.searchBooks(
                title1, title2, title3,
                input1, input2, input3,
                operator1, operator2,
                book_type, book_lang,
                start_year, end_year);

        // 모델에 결과를 추가하여 템플릿으로 전달
        model.addAttribute("books", books);

        // searchResult.html 템플릿을 렌더링하여 반환
        return "searchResult";
    }

    @GetMapping("/book/details/{bookId}")
    public String showBookDetails(@PathVariable Integer bookId, Model model) {
        // bookId를 가져와서 BookCode로 책 정보 조회
        Book book = bookRepository.getByBookCode(bookId);

        // 모델에 결과를 추가하여 템플릿으로 전달
        model.addAttribute("book", book);

        return "book-details";
    }
}