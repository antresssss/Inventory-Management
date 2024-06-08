package com.example.inventory.dao;

import com.example.inventory.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO products (name, quantity, price) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, product.getName());
            ps.setInt(2, product.getQuantity());
            ps.setDouble(3, product.getPrice());
            ps.executeUpdate();
        }
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);
            }
        }
        return products;
    }
}
