package com.example.inventory.ui;

import com.example.inventory.dao.CustomerDAO;
import com.example.inventory.model.Customer;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewCustomersPanel extends JPanel {
    private CustomerDAO customerDAO;
    private JTable customerTable;
    private DefaultTableModel tableModel;

    public ViewCustomersPanel(Connection connection) {
        customerDAO = new CustomerDAO(connection);
        setLayout(new BorderLayout());

        // Set up the table model with column names
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone"}, 0);
        customerTable = new JTable(tableModel);

        // Customize the table appearance
        customerTable.setFont(new Font("Arial", Font.PLAIN, 14));
        customerTable.setRowHeight(30);
        customerTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        customerTable.getTableHeader().setBackground(Color.BLACK);
        customerTable.getTableHeader().setForeground(Color.BLACK);
        customerTable.setBackground(Color.BLACK);
        customerTable.setForeground(Color.WHITE);

        // Center the table within a scroll pane
        JScrollPane scrollPane = new JScrollPane(customerTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));  // Set preferred size for the scroll pane

        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.add(scrollPane);  // Add scroll pane to a panel with GridBagLayout for centering

        add(tablePanel, BorderLayout.CENTER);  // Add table panel to the center

        JButton loadButton = new JButton("Load Customers");
        loadButton.addActionListener(e -> loadCustomers());
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadCustomers() {
        try {
            List<Customer> customers = customerDAO.getAllCustomers();
            tableModel.setRowCount(0);  // Clear existing data
            for (Customer customer : customers) {
                tableModel.addRow(new Object[]{
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPhone()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading customers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
