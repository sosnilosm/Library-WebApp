package com.sosnilosm.webapp.customer.controller;

import com.sosnilosm.webapp.customer.dao.CustomerDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sergei Sosnilo
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerDAO customerDAO;

    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping()
    public String customerMain(Model model) {
        model.addAttribute("customers", customerDAO.selectAllCustomers());
        return "/customer/customer-main";
    }

    @GetMapping("/{id}")
    public String showCustomer(Model model, @PathVariable int id) {
        model.addAttribute("customer", customerDAO.selectOneCustomer(id));
        return "/customer/customer-show";
    }
}
