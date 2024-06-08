package com.example.inventory.ui;

import com.example.inventory.dao.CustomerDAO;
import com.example.inventory.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class AddCustomerPanel extends JPanel {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private CustomerDAO customerDAO;

    public AddCustomerPanel(Connection connection) {
        customerDAO = new CustomerDAO(connection);

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

        JButton addButton = new JButton("Add Customer");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Customer customer = new Customer();
                    customer.setName(nameField.getText());
                    customer.setEmail(emailField.getText());
                    customer.setPhone(phoneField.getText());
                    customerDAO.addCustomer(customer);
                    JOptionPane.showMessageDialog(AddCustomerPanel.this, "Customer added successfully.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(AddCustomerPanel.this, "Error adding customer: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(addButton);
    }
}
