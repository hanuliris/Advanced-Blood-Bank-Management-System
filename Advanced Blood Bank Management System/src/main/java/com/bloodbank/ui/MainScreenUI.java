package com.bloodbank.ui;

import com.bloodbank.model.BloodStock;
import com.bloodbank.service.InventoryService;

import javax.swing.*;
import java.awt.*;

public class MainScreenUI extends JFrame {

    public MainScreenUI() {
        setTitle("Advanced Blood Bank Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center the window

        // Shared inventory object
        BloodStock stock = new BloodStock();
        InventoryService inventoryService = new InventoryService(stock);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel heading = new JLabel("Welcome to Blood Bank System", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(heading);

        JButton adminLoginBtn = new JButton("Login as Admin");
        adminLoginBtn.addActionListener(e -> {
            dispose();
            new AdminLoginWindow(inventoryService).setVisible(true);
        });
        panel.add(adminLoginBtn);

        JButton donorLoginBtn = new JButton("Login as Donor");
        donorLoginBtn.addActionListener(e -> {
            dispose();
            new DonorLoginUI(inventoryService).setVisible(true);
        });
        panel.add(donorLoginBtn);

        JButton registerDonorBtn = new JButton("Register as Donor");
        registerDonorBtn.addActionListener(e -> {
            dispose();
            new DonorRegistrationForm().setVisible(true);
        });
        panel.add(registerDonorBtn);

        add(panel);
        setVisible(true);
    }

    // Entry point for the whole application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainScreenUI::new);
    }
}
