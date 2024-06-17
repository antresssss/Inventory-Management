package com.example.inventory.ui;

import com.example.inventory.dao.ProductDAO;
import com.example.inventory.model.Product;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

public class AddProductPanel extends JPanel {
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;
    private ProductDAO productDAO;

    public AddProductPanel(Connection connection) {
        productDAO = new ProductDAO(connection);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components

        // Add image at the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        ImageIcon imageIcon = new ImageIcon("src/com/example/inventory/assets/img4.jpeg"); 
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        add(imageLabel, gbc);

        // Reset gridwidth for the rest of the components
        gbc.gridwidth = 1;

        // Add Name Label and TextField
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);

        // Add Quantity Label and TextField
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        quantityField = new JTextField(20);
        add(quantityField, gbc);

        // Add Price Label and TextField
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        priceField = new JTextField(20);
        add(priceField, gbc);

        // Add Add Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
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
        add(addButton, gbc);
    }
}
