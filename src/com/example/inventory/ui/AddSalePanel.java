package com.example.inventory.ui;

import com.example.inventory.dao.SaleDAO;
import com.example.inventory.model.Sale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class AddSalePanel extends JPanel {
    private JTextField productIdField;
    private JTextField customerIdField;
    private JTextField quantityField;
    private JTextField totalAmountField;
    private SaleDAO saleDAO;

    public AddSalePanel(Connection connection) {
        saleDAO = new SaleDAO(connection);

        setLayout(new GridLayout(5, 2));

        add(new JLabel("Product ID:"));
        productIdField = new JTextField();
        add(productIdField);

        add(new JLabel("Customer ID:"));
        customerIdField = new JTextField();
        add(customerIdField);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);

        add(new JLabel("Total Amount:"));
        totalAmountField = new JTextField();
        add(totalAmountField);

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
        add(addButton);
    }
}
