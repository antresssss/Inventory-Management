package com.example.inventory.ui;

import com.example.inventory.dao.SupplierDAO;
import com.example.inventory.model.Supplier;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

public class AddSupplierPanel extends JPanel {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private SupplierDAO supplierDAO;

    public AddSupplierPanel(Connection connection) {
        supplierDAO = new SupplierDAO(connection);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add an image at the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        ImageIcon imageIcon = new ImageIcon("src/com/example/inventory/assets/img2.jpeg");
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        add(imageLabel, gbc);

        // Reset gridwidth for other components
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(20);
        add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
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
        add(addButton, gbc);
    }
}
