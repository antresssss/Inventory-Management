package com.example.inventory;

import com.example.inventory.ui.MainFrame;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/inventory_db", "user1", "12345678");
                MainFrame mainFrame = new MainFrame(connection);
                mainFrame.setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error connecting to database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
