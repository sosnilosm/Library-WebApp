package com.sosnilosm.webapp.book.controller;

import com.sosnilosm.webapp.book.dao.BookDAO;
import com.sosnilosm.webapp.book.entity.Book;
import com.sosnilosm.webapp.customer.dao.CustomerDAO;
import com.sosnilosm.webapp.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Sergei Sosnilo
 */
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final CustomerDAO customerDAO;

    @Autowired
    public BookController(BookDAO bookDAO, CustomerDAO customerDAO) {
        this.bookDAO = bookDAO;
        this.customerDAO = customerDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.selectAllBooks());
        return "/books/book-main";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable int id, @ModelAttribute("customer") Customer customer) {
        model.addAttribute("book", bookDAO.selectOneBook(id));
        model.addAttribute("customers", customerDAO.selectAllCustomers());
        return "/books/book-show";
    }

    @GetMapping("/new")
    public String creat(@ModelAttribute("book") Book book) {
        return "books/book-new";
    }

    @PostMapping()
    public String add(@ModelAttribute("book") @Valid Book book,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/books/book-new";
        }
        bookDAO.insertNewBook(book);
        return "redirect:/books/";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.selectOneBook(id));
        return "/books/book-edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") Book book,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/books/book-edit";
        }
        bookDAO.updateOneBook(book);
        return "redirect:/books/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.deleteOneBook(id);
        return "redirect:/books/";
    }

    @PostMapping("/{id}/issue")
    public String issue(@PathVariable("id") int book_id, @ModelAttribute("customer") Customer customer) {
        bookDAO.issueBookToCustomer(customer.getId(), book_id);
        return "redirect:/customers/";
    }
}
