package com.example.inventory.dao;

import com.example.inventory.model.Supplier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    private Connection connection;

    public SupplierDAO(Connection connection) {
        this.connection = connection;
    }

    public void addSupplier(Supplier supplier) throws SQLException {
        String query = "INSERT INTO suppliers (name, email, phone) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getEmail());
            ps.setString(3, supplier.getPhone());
            ps.executeUpdate();
        }
    }

    public List<Supplier> getAllSuppliers() throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM suppliers";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                supplier.setEmail(rs.getString("email"));
                supplier.setPhone(rs.getString("phone"));
                suppliers.add(supplier);
            }
        }
        return suppliers;
    }
}
