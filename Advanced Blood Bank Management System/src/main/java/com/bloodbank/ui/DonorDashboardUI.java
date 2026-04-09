package com.bloodbank.ui;

import com.bloodbank.model.BloodStock;
import com.bloodbank.model.EmergencyRequest;
import com.bloodbank.service.EmergencyRequestHandler;
import com.bloodbank.service.InventoryService;

import javax.swing.*;
import java.awt.*;

public class DonorDashboardUI extends JFrame {

    private final InventoryService inventoryService;
    private final EmergencyRequestHandler emergencyManager;

    public DonorDashboardUI(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.emergencyManager = EmergencyRequestHandler.getInstance();

        setTitle("Donor Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
        setVisible(true);
    }

    private void initUI() {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Donate Panel
        JPanel donatePanel = new JPanel(new BorderLayout());
        JPanel donateForm = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel donateTitle = new JLabel("Donate Blood", SwingConstants.CENTER);
        donateTitle.setFont(new Font("Arial", Font.BOLD, 16));
        donatePanel.add(donateTitle, BorderLayout.NORTH);

        JComboBox<String> bloodTypeCombo = new JComboBox<>(getBloodTypes());
        JTextField unitsField = new JTextField();
        JButton donateBtn = new JButton("Donate");

        donateForm.add(new JLabel("Blood Type:"));
        donateForm.add(bloodTypeCombo);
        donateForm.add(new JLabel("Units:"));
        donateForm.add(unitsField);
        donateForm.add(new JLabel("")); // empty for spacing
        donateForm.add(donateBtn);

        donatePanel.add(donateForm, BorderLayout.CENTER);

        donateBtn.addActionListener(e -> {
            try {
                String type = bloodTypeCombo.getSelectedItem().toString();
                int units = Integer.parseInt(unitsField.getText().trim());
                if (units <= 0) throw new NumberFormatException();

                inventoryService.donateBlood(type, units);
                JOptionPane.showMessageDialog(this, "Thank you for your donation!");
                unitsField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number of units.");
            }
        });

        // Request Panel
        JPanel requestPanel = new JPanel(new BorderLayout());
        JPanel requestForm = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel requestTitle = new JLabel("Request Blood", SwingConstants.CENTER);
        requestTitle.setFont(new Font("Arial", Font.BOLD, 16));
        requestPanel.add(requestTitle, BorderLayout.NORTH);

        JTextField nameField = new JTextField();
        JComboBox<String> reqBloodCombo = new JComboBox<>(getBloodTypes());
        JTextField urgencyField = new JTextField();
        JButton requestBtn = new JButton("Request");

        requestForm.add(new JLabel("Patient Name:"));
        requestForm.add(nameField);
        requestForm.add(new JLabel("Blood Type:"));
        requestForm.add(reqBloodCombo);
        requestForm.add(new JLabel("Urgency (1-10):"));
        requestForm.add(urgencyField);
        requestForm.add(new JLabel(""));
        requestForm.add(requestBtn);

        requestPanel.add(requestForm, BorderLayout.CENTER);

        requestBtn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                int urgency = Integer.parseInt(urgencyField.getText().trim());
                String bloodType = reqBloodCombo.getSelectedItem().toString();

                if (name.isEmpty() || urgency < 1 || urgency > 10) {
                    JOptionPane.showMessageDialog(this, "Please fill in valid details.");
                    return;
                }

                emergencyManager.getQueue().add(new EmergencyRequest(name, bloodType, urgency));
                JOptionPane.showMessageDialog(this, "Request submitted successfully.");
                nameField.setText("");
                urgencyField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Urgency must be a number between 1 and 10.");
            }
        });

        // Add Tabs
        tabbedPane.addTab("Donate", donatePanel);
        tabbedPane.addTab("Request", requestPanel);

        add(tabbedPane);
    }

    private String[] getBloodTypes() {
        return new String[]{"O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+"};
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BloodStock stock = new BloodStock();
            InventoryService inventoryService = new InventoryService(stock);
            new DonorDashboardUI(inventoryService);
        });
    }
}
