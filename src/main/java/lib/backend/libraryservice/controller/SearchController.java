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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import lib.backend.libraryservice.Entity.Book;
import lib.backend.libraryservice.Entity.User;
import lib.backend.libraryservice.repository.BookRepository;
import lib.backend.libraryservice.service.BookService;
import lib.backend.libraryservice.service.BorrowService;

@Controller
public class SearchController {

    BookRepository bookRepository;
    BookService bookService;
    BorrowService borrowService;

    public SearchController(BookRepository bookRepository, BookService bookService, BorrowService borrowService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    @GetMapping("/search-page")
    public String LibrarySearchPage() {
        return "LibrarySearchPage";
    }

    @PostMapping("/search_mini")
    public String searchBook(
            @RequestParam("input1") String input1, Model model) {
        return "searchResult";
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
            @RequestParam("type") String type,
            @RequestParam("lang") String lang,
            @RequestParam("start_year") Integer start_year,
            @RequestParam("end_year") Integer end_year,
            Model model) {
        // BookService를 사용하여 책 검색
        List<Book> books = bookService.searchBooks(
                title1, title2, title3,
                input1, input2, input3,
                operator1, operator2,
                type, lang,
                start_year, end_year);
        // 모델에 결과를 추가하여 템플릿으로 전달
        model.addAttribute("books", books);

        // searchResult.html 템플릿을 렌더링하여 반환
        return "searchResult";
    }

    @GetMapping("/book/details/{bookId}")
    public String showBookDetails(@PathVariable Integer bookId, Model model, HttpSession session) {
        Book book = bookRepository.getByBookCode(bookId);
        User user = (User) session.getAttribute("user");
        switch (book.getLang()) {
            case "kr":
                book.setLang("한국어");
                break;
            case "en":
                book.setLang("영어");
                break;
            case "jp":
                book.setLang("일본어");
                break;
            case "ch":
                book.setLang("중국어");
                break;
            default:
                book.setLang("한국어");
                break;
        }
        // 모델에 결과를 추가하여 템플릿으로 전달
        model.addAttribute("book", book);
        Integer bookStatus = borrowService.borrowBtnCondition2(bookId);
        model.addAttribute("bookStatus", bookStatus);
        if (user == null) {
            model.addAttribute("showButton", borrowService.borrowBtnCondition2(bookId));
        } else if (user.getUser_name().equals("admin")) {
            model.addAttribute("showButton", 4);
        } else {
            model.addAttribute("showButton", borrowService.borrowBtnCondition(bookId, user.getUser_num()));
        }
        // 대출 가능 : 0,
        // 대출중 - 예약가능 : 1,
        // 대출중인 유저가 들어갔을때, - 반납가능 : 2,
        // 예약한 유저가 들어갔을때, 예약취소 가능 : 3
        // 관리자가 들어갈때 : 4
        return "book-details";
    }
}