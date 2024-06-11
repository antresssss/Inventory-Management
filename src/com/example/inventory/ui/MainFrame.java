package com.example.inventory.ui;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(Connection connection) {
        setTitle("Inventory Management System");
        setSize(1000, 700);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

      
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14)); 
        tabbedPane.setBackground(Color.BLACK); 
        tabbedPane.setForeground(Color.WHITE);  
        // Customize tab components
        UIManager.put("TabbedPane.selected", Color.DARK_GRAY);  
        UIManager.put("TabbedPane.contentAreaColor", Color.BLACK); 
        UIManager.put("TabbedPane.borderHightlightColor", Color.WHITE); 
        UIManager.put("TabbedPane.darkShadow", Color.GRAY);

        tabbedPane.add("Add Customer", new AddCustomerPanel(connection));
        tabbedPane.add("View Customers", new ViewCustomersPanel(connection));
        tabbedPane.add("Add Product", new AddProductPanel(connection));
        tabbedPane.add("View Products", new ViewProductsPanel(connection));
        tabbedPane.add("Add Sale", new AddSalePanel(connection));
        tabbedPane.add("View Sales", new ViewSalesPanel(connection));
        tabbedPane.add("Add Supplier", new AddSupplierPanel(connection));
        tabbedPane.add("View Suppliers", new ViewSuppliersPanel(connection));

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        
            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/inventory_db", "user1","12345678");
                MainFrame mainFrame = new MainFrame(connection);
                mainFrame.setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error connecting to database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
