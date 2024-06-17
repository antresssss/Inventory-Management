package com.example.inventory.ui;

import com.example.inventory.dao.SupplierDAO;
import com.example.inventory.model.Supplier;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewSuppliersPanel extends JPanel {
    private SupplierDAO supplierDAO;
    private JTable supplierTable;
    private DefaultTableModel tableModel;

    public ViewSuppliersPanel(Connection connection) {
        supplierDAO = new SupplierDAO(connection);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone"}, 0);
        supplierTable = new JTable(tableModel);

        supplierTable.setFont(new Font("Arial", Font.PLAIN, 14));
        supplierTable.setRowHeight(30);
        supplierTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        supplierTable.getTableHeader().setBackground(Color.BLACK);
        supplierTable.getTableHeader().setForeground(Color.BLACK);
        supplierTable.setBackground(Color.BLACK);
        supplierTable.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(supplierTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.add(scrollPane);

        add(tablePanel, BorderLayout.CENTER);

        JButton loadButton = new JButton("Load Suppliers");
        loadButton.addActionListener(e -> loadSuppliers());
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadSuppliers() {
        try {
            List<Supplier> suppliers = supplierDAO.getAllSuppliers();
            tableModel.setRowCount(0);  // Clear existing data
            for (Supplier supplier : suppliers) {
                tableModel.addRow(new Object[]{
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getEmail(),
                        supplier.getPhone()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading suppliers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
