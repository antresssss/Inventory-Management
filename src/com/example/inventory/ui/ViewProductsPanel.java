package com.example.inventory.ui;

import com.example.inventory.dao.ProductDAO;
import com.example.inventory.model.Product;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewProductsPanel extends JPanel {
    private ProductDAO productDAO;
    private JTable productTable;
    private DefaultTableModel tableModel;

    public ViewProductsPanel(Connection connection) {
        productDAO = new ProductDAO(connection);
        setLayout(new BorderLayout());

        // Set up the table model with column names
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Quantity", "Price"}, 0);
        productTable = new JTable(tableModel);

        // Customize the table appearance
        productTable.setFont(new Font("Arial", Font.PLAIN, 14));
        productTable.setRowHeight(30);
        productTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        productTable.getTableHeader().setBackground(Color.BLACK);
        productTable.getTableHeader().setForeground(Color.BLACK);
        productTable.setBackground(Color.BLACK);
        productTable.setForeground(Color.WHITE);

        // Center the table within a scroll pane
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.add(scrollPane);

        add(tablePanel, BorderLayout.CENTER);

        JButton loadButton = new JButton("Load Products");
        loadButton.addActionListener(e -> loadProducts());
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadProducts() {
        try {
            List<Product> products = productDAO.getAllProducts();
            tableModel.setRowCount(0);  // Clear existing data
            for (Product product : products) {
                tableModel.addRow(new Object[]{
                        product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.getPrice()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
