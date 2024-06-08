package com.example.inventory.ui;

import com.example.inventory.dao.CustomerDAO;
import com.example.inventory.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ViewCustomersPanel extends JPanel {
    private CustomerDAO customerDAO;
    private JTextArea textArea;

    public ViewCustomersPanel(Connection connection) {
        customerDAO = new CustomerDAO(connection);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton loadButton = new JButton("Load Customers");
        loadButton.addActionListener(e -> loadCustomers());
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadCustomers() {
        try {
            List<Customer> customers = customerDAO.getAllCustomers();
            textArea.setText("");
            for (Customer customer : customers) {
                textArea.append("ID: " + customer.getId() + "\n");
                textArea.append("Name: " + customer.getName() + "\n");
                textArea.append("Email: " + customer.getEmail() + "\n");
                textArea.append("Phone: " + customer.getPhone() + "\n");
                textArea.append("\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading customers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
