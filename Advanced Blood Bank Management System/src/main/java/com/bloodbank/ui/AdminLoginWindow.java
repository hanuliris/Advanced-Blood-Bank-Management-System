package com.bloodbank.ui;

import com.bloodbank.service.AuthService;
import com.bloodbank.service.InventoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private InventoryService inventoryService;

    public AdminLoginWindow(InventoryService inventoryService) {
        this.inventoryService = inventoryService;

        setTitle("Admin Login");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 100, 20);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 120, 20);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 60, 100, 20);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 60, 120, 20);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(90, 100, 100, 30);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AuthService auth = new AuthService();
                boolean success = auth.validateLogin(
                        usernameField.getText(),
                        new String(passwordField.getPassword()),
                        "admin"
                );

                if (success) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    new AdminDashboardUI(inventoryService); // pass inventoryService
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials");
                }
            }
        });

        setVisible(true);
    }
}
