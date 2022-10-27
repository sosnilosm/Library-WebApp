package com.sosnilosm.webapp.customer.controller;

import com.sosnilosm.webapp.book.dao.BookDAO;
import com.sosnilosm.webapp.book.entity.Book;
import com.sosnilosm.webapp.customer.dao.CustomerDAO;
import com.sosnilosm.webapp.customer.entity.Customer;
import com.sosnilosm.webapp.customer.util.CustomerValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Sergei Sosnilo
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerDAO customerDAO;
    private final CustomerValidator customerValidator;
    private final BookDAO bookDAO;

    public CustomerController(CustomerDAO customerDAO, CustomerValidator customerValidator, BookDAO bookDAO) {
        this.customerDAO = customerDAO;
        this.customerValidator = customerValidator;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("customers", customerDAO.selectAllCustomers());
        return "/customers/customer-main";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable int id, @ModelAttribute("book") Book book) {
        model.addAttribute("customer", customerDAO.selectOneCustomer(id));
        model.addAttribute("books", bookDAO.selectCustomersBooks(id));
        return "/customers/customer-show";
    }

    @GetMapping("/new")
    public String creat(@ModelAttribute("customer") Customer customer) {
        return "/customers/customer-new";
    }

    @PostMapping()
    public String add(@ModelAttribute("customer") @Valid Customer customer,
                                BindingResult bindingResult) {
        customerValidator.validate(customer, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/customers/customer-new";
        }
        customerDAO.insertNewCustomer(customer);
        return "redirect:/customers/";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerDAO.selectOneCustomer(id));
        return "customers/customer-edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("customer") Customer customer,
                         BindingResult bindingResult) {
        customerValidator.validate(customer, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/customers/customer-edit";
        }
        customerDAO.updateOneCustomer(customer);
        return "redirect:/customers/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        customerDAO.deleteOneCustomer(id);
        return "redirect:/customers";
    }

    @DeleteMapping("/{id}/refund/")
    public String refund(@PathVariable("id") int customer_id, @ModelAttribute("book") Book book) {
        bookDAO.refundBookFromCustomer(customer_id, book.getId());
        return "redirect:/customers/";
    }
}
