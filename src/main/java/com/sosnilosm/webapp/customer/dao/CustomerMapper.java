package com.sosnilosm.webapp.customer.dao;

import com.sosnilosm.webapp.customer.entity.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sergei Sosnilo
 */
public class CustomerMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();

        customer.setId(rs.getInt("id"));
        customer.setFirstname(rs.getString("firstname"));
        customer.setLastname(rs.getString("lastname"));
        customer.setEmail(rs.getString("email"));
        customer.setBdate(rs.getDate("bdate"));
        customer.setPassport(rs.getString("passport"));
        customer.setAddress(rs.getString("address"));

        return customer;
    }
}
