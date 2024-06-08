package com.example.inventory.ui;

import com.example.inventory.dao.ProductDAO;
import com.example.inventory.model.Product;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ViewProductsPanel extends JPanel {
    private ProductDAO productDAO;
    private JTextArea textArea;

    public ViewProductsPanel(Connection connection) {
        productDAO = new ProductDAO(connection);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton loadButton = new JButton("Load Products");
        loadButton.addActionListener(e -> loadProducts());
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadProducts() {
        try {
            List<Product> products = productDAO.getAllProducts();
            textArea.setText("");
            for (Product product : products) {
                textArea.append("ID: " + product.getId() + "\n");
                textArea.append("Name: " + product.getName() + "\n");
                textArea.append("Quantity: " + product.getQuantity() + "\n");
                textArea.append("Price: " + product.getPrice() + "\n");
                textArea.append("\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
