package com.example.inventory.ui;

import com.example.inventory.dao.CustomerDAO;
import com.example.inventory.model.Customer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

public class AddCustomerPanel extends JPanel {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private CustomerDAO customerDAO;

    public AddCustomerPanel(Connection connection) {
        customerDAO = new CustomerDAO(connection);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components

        // Add image at the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        ImageIcon imageIcon = new ImageIcon("src/com/example/inventory/assets/img1.jpeg"); 
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

        // Add Email Label and TextField
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        add(emailField, gbc);

        // Add Phone Label and TextField
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(20);
        add(phoneField, gbc);

        // Add Add Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
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
        add(addButton, gbc);
    }
}
