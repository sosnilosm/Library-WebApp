package com.sosnilosm.webapp.customer.dao;

import com.sosnilosm.webapp.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Sergei Sosnilo
 */
@Component
public class CustomerDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> selectAllCustomers() {
        return jdbcTemplate.query(
                "SELECT * FROM Customer ORDER BY id",
                new BeanPropertyRowMapper<>(Customer.class)
        );
    }

    public Customer selectOneCustomer(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Customer WHERE id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Customer.class) // BeanPropertyRowMapper(Customer.class)
        ).stream().findAny().orElse(null);
    }

    public void insertNewCustomer(Customer customer) {
        jdbcTemplate.update(
                "INSERT INTO Customer(firstname, lastname, email, bdate, passport, address) " +
                        "VALUES(?, ?, ?, ?, ?, ?)",
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getBdate(),
                customer.getPassport(),
                customer.getAddress()
        );
    }

    public void updateOneCustomer(Customer customer) {
        jdbcTemplate.update(
                "UPDATE Customer " +
                        "SET firstname = ?, lastname = ?, email = ?, bdate = ?, passport = ?, address = ? WHERE id = ?",
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getBdate(),
                customer.getPassport(),
                customer.getAddress(),
                customer.getId()
        );
    }

    public void deleteOneCustomer(int id) {
        jdbcTemplate.update(
                "DELETE FROM Customer WHERE id =?",
                id
        );
    }
}
