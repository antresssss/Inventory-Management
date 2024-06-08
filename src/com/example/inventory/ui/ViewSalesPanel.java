package com.example.inventory.ui;

import com.example.inventory.dao.SaleDAO;
import com.example.inventory.model.Sale;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ViewSalesPanel extends JPanel {
    private SaleDAO saleDAO;
    private JTextArea textArea;

    public ViewSalesPanel(Connection connection) {
        saleDAO = new SaleDAO(connection);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton loadButton = new JButton("Load Sales");
        loadButton.addActionListener(e -> loadSales());
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadSales() {
        try {
            List<Sale> sales = saleDAO.getAllSales();
            textArea.setText("");
            for (Sale sale : sales) {
                textArea.append("ID: " + sale.getId() + "\n");
                textArea.append("Product ID: " + sale.getProductId() + "\n");
                textArea.append("Customer ID: " + sale.getCustomerId() + "\n");
                textArea.append("Quantity: " + sale.getQuantity() + "\n");
                textArea.append("Total Amount: " + sale.getTotalAmount() + "\n");
                textArea.append("\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading sales: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
