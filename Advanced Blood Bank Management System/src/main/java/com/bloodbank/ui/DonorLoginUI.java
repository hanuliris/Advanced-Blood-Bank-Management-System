package com.bloodbank.ui;

import com.bloodbank.service.InventoryService;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DonorLoginUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private final InventoryService inventoryService;

    public DonorLoginUI(InventoryService inventoryService) {
        this.inventoryService = inventoryService;

        setTitle("Donor Login");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> handleLogin());

        panel.add(new JLabel()); // spacer
        panel.add(loginButton);

        add(panel);
    }

    private void handleLogin() {
        String inputUsername = usernameField.getText().trim();
        String inputPassword = new String(passwordField.getPassword());

        if (validateCredentials(inputUsername, inputPassword)) {
            this.dispose();
            DonorDashboardUI donorDashboardUI = new DonorDashboardUI(inventoryService);
            donorDashboardUI.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("donors.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading login file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
