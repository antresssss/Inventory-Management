package com.example.inventory.ui;

import com.example.inventory.dao.ProductDAO;
import com.example.inventory.model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class AddProductPanel extends JPanel {
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;
    private ProductDAO productDAO;

    public AddProductPanel(Connection connection) {
        productDAO = new ProductDAO(connection);

        setLayout(new GridLayout(4, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Product product = new Product();
                    product.setName(nameField.getText());
                    product.setQuantity(Integer.parseInt(quantityField.getText()));
                    product.setPrice(Double.parseDouble(priceField.getText()));
                    productDAO.addProduct(product);
                    JOptionPane.showMessageDialog(AddProductPanel.this, "Product added successfully.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(AddProductPanel.this, "Error adding product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(addButton);
    }
}
