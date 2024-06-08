package com.example.inventory.ui;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class MainFrame extends JFrame {
    public MainFrame(Connection connection) {
        setTitle("Inventory Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

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
}
