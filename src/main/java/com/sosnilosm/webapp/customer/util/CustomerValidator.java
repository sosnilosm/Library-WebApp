package com.sosnilosm.webapp.customer.util;

import com.sosnilosm.webapp.customer.dao.CustomerDAO;
import com.sosnilosm.webapp.customer.entity.Customer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Sergei Sosnilo
 */
@Component
public class CustomerValidator implements Validator {
    private final CustomerDAO customerDAO;

    public CustomerValidator(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;

        if (customerDAO.selectOneCustomer(customer.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "Email is already taken");
        }
    }
}
