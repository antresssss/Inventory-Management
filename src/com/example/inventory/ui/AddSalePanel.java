package com.example.inventory.ui;

import com.example.inventory.dao.SaleDAO;
import com.example.inventory.model.Sale;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

public class AddSalePanel extends JPanel {
    private JTextField productIdField;
    private JTextField customerIdField;
    private JTextField quantityField;
    private JTextField totalAmountField;
    private SaleDAO saleDAO;

    public AddSalePanel(Connection connection) {
        saleDAO = new SaleDAO(connection);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components

        // Add image at the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        ImageIcon imageIcon = new ImageIcon("src/com/example/inventory/assets/img3.jpeg"); 
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        add(imageLabel, gbc);

        // Reset gridwidth for the rest of the components
        gbc.gridwidth = 1;

        // Add Product ID Label and TextField
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Product ID:"), gbc);
        gbc.gridx = 1;
        productIdField = new JTextField(20);
        add(productIdField, gbc);

        // Add Customer ID Label and TextField
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Customer ID:"), gbc);
        gbc.gridx = 1;
        customerIdField = new JTextField(20);
        add(customerIdField, gbc);

        // Add Quantity Label and TextField
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        quantityField = new JTextField(20);
        add(quantityField, gbc);

        // Add Total Amount Label and TextField
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Total Amount:"), gbc);
        gbc.gridx = 1;
        totalAmountField = new JTextField(20);
        add(totalAmountField, gbc);

        // Add Add Button
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addButton = new JButton("Add Sale");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Sale sale = new Sale();
                    sale.setProductId(Integer.parseInt(productIdField.getText()));
                    sale.setCustomerId(Integer.parseInt(customerIdField.getText()));
                    sale.setQuantity(Integer.parseInt(quantityField.getText()));
                    sale.setTotalAmount(Double.parseDouble(totalAmountField.getText()));
                    saleDAO.addSale(sale);
                    JOptionPane.showMessageDialog(AddSalePanel.this, "Sale added successfully.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(AddSalePanel.this, "Error adding sale: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(addButton, gbc);
    }
}
