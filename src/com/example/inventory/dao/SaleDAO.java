package com.example.inventory.dao;

import com.example.inventory.model.Sale;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {
    private Connection connection;

    public SaleDAO(Connection connection) {
        this.connection = connection;
    }

    public void addSale(Sale sale) throws SQLException {
        String query = "INSERT INTO sales (product_id, customer_id, quantity, total_amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, sale.getProductId());
            ps.setInt(2, sale.getCustomerId());
            ps.setInt(3, sale.getQuantity());
            ps.setDouble(4, sale.getTotalAmount());
            ps.executeUpdate();
        }
    }

    public List<Sale> getAllSales() throws SQLException {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM sales";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setId(rs.getInt("id"));
                sale.setProductId(rs.getInt("product_id"));
                sale.setCustomerId(rs.getInt("customer_id"));
                sale.setQuantity(rs.getInt("quantity"));
                sale.setTotalAmount(rs.getDouble("total_amount"));
                sales.add(sale);
            }
        }
        return sales;
    }
}
