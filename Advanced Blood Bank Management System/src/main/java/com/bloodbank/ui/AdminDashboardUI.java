package com.bloodbank.ui;

import com.bloodbank.model.BloodStock;
import com.bloodbank.service.InventoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboardUI extends JFrame {

    private final InventoryService inventoryService;

    public AdminDashboardUI(InventoryService inventoryService) {
        this.inventoryService = inventoryService;

        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window

        JButton btnViewDonors = new JButton("View All Donors");
        btnViewDonors.setBounds(100, 30, 200, 40);
        add(btnViewDonors);

        JButton btnEmergency = new JButton("Handle Emergency Requests");
        btnEmergency.setBounds(100, 90, 200, 40);
        add(btnEmergency);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(100, 150, 200, 40);
        add(btnLogout);

        JButton searchBtn = new JButton("Search Donors");
        searchBtn.setBounds(100, 210, 200, 40);
        searchBtn.addActionListener(e -> new BloodSearchUI());
        add(searchBtn);

        //JButton viewRequestsBtn = new JButton("View Emergency Requests");
       // viewRequestsBtn.setBounds(100, 270, 200, 40);
        //viewRequestsBtn.addActionListener(e -> new EmergencyRequestHandlerUI(inventoryService));
        //add(viewRequestsBtn);

        // Action Listeners
        btnViewDonors.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DonorTableUI(); // View donors window
            }
        });

        btnEmergency.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EmergencyRequestHandlerUI(inventoryService); // Emergency queue window
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close dashboard
                new AdminLoginWindow(inventoryService); // Back to login screen with shared state
            }
        });

        setVisible(true);
    }

    // ✅ Optional: remove this if not testing directly
    public static void main(String[] args) {
        // For testing, you can pass a dummy InventoryService:
        new AdminDashboardUI(new InventoryService(new BloodStock()));
    }
}
