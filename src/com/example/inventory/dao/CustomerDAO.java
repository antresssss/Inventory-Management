package com.example.inventory.dao;

import com.example.inventory.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO customers (name, email, phone) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPhone());
            ps.executeUpdate();
        }
    }

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customers.add(customer);
            }
        }
        return customers;
    }
}
