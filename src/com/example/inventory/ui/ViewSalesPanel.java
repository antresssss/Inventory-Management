package com.example.inventory.ui;

import com.example.inventory.dao.SaleDAO;
import com.example.inventory.model.Sale;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewSalesPanel extends JPanel {
    private SaleDAO saleDAO;
    private JTable salesTable;
    private DefaultTableModel tableModel;

    public ViewSalesPanel(Connection connection) {
        saleDAO = new SaleDAO(connection);
        setLayout(new BorderLayout());

        // Set up the table model with column names
        tableModel = new DefaultTableModel(new String[]{"ID", "Product ID", "Customer ID", "Quantity", "Total Amount"}, 0);
        salesTable = new JTable(tableModel);

        // Customize the table appearance
        salesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        salesTable.setRowHeight(30);
        salesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        salesTable.getTableHeader().setBackground(Color.BLACK);
        salesTable.getTableHeader().setForeground(Color.BLACK);
        salesTable.setBackground(Color.BLACK);
        salesTable.setForeground(Color.WHITE);

        // Center the table within a scroll pane
        JScrollPane scrollPane = new JScrollPane(salesTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.add(scrollPane);

        add(tablePanel, BorderLayout.CENTER);

        JButton loadButton = new JButton("Load Sales");
        loadButton.addActionListener(e -> loadSales());
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadSales() {
        try {
            List<Sale> sales = saleDAO.getAllSales();
            tableModel.setRowCount(0);  // Clear existing data
            for (Sale sale : sales) {
                tableModel.addRow(new Object[]{
                        sale.getId(),
                        sale.getProductId(),
                        sale.getCustomerId(),
                        sale.getQuantity(),
                        sale.getTotalAmount()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading sales: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
