package com.example.inventory.ui;

import com.example.inventory.dao.SupplierDAO;
import com.example.inventory.model.Supplier;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ViewSuppliersPanel extends JPanel {
    private SupplierDAO supplierDAO;
    private JTextArea textArea;

    public ViewSuppliersPanel(Connection connection) {
        supplierDAO = new SupplierDAO(connection);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton loadButton = new JButton("Load Suppliers");
        loadButton.addActionListener(e -> loadSuppliers());
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadSuppliers() {
        try {
            List<Supplier> suppliers = supplierDAO.getAllSuppliers();
            textArea.setText("");
            for (Supplier supplier : suppliers) {
                textArea.append("ID: " + supplier.getId() + "\n");
                textArea.append("Name: " + supplier.getName() + "\n");
                textArea.append("Email: " + supplier.getEmail() + "\n");
                textArea.append("Phone: " + supplier.getPhone() + "\n");
                textArea.append("\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading suppliers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
