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
}
