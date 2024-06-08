package com.example.inventory.ui;

import com.example.inventory.dao.SupplierDAO;
import com.example.inventory.model.Supplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class AddSupplierPanel extends JPanel {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private SupplierDAO supplierDAO;

    public AddSupplierPanel(Connection connection) {
        supplierDAO = new SupplierDAO(connection);

        setLayout(new GridLayout(4, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Phone:"));
        phoneField = new JTextField();
        add(phoneField);

        JButton addButton = new JButton("Add Supplier");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Supplier supplier = new Supplier();
                    supplier.setName(nameField.getText());
                    supplier.setEmail(emailField.getText());
                    supplier.setPhone(phoneField.getText());
                    supplierDAO.addSupplier(supplier);
                    JOptionPane.showMessageDialog(AddSupplierPanel.this, "Supplier added successfully.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(AddSupplierPanel.this, "Error adding supplier: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(addButton);
    }
}
