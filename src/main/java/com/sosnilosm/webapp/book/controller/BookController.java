package com.sosnilosm.webapp.book.controller;

import com.sosnilosm.webapp.book.dao.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sergei Sosnilo
 */
@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String bookMain(Model model) {
        model.addAttribute("books", bookDAO.selectAllBooks());
        return "/book/book-main";
    }

    @GetMapping("/{id}")
    public String showCustomer(Model model, @PathVariable int id) {
        model.addAttribute("book", bookDAO.selectOneBook(id));
        return "/book/book-show";
    }
}
