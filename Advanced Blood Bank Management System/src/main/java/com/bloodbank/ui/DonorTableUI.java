package com.bloodbank.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DonorTableUI extends JFrame {
    private JTable donorTable;
    private DefaultTableModel tableModel;

    public DonorTableUI() {
        setTitle("All Registered Donors");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table column names
        String[] columnNames = {"Donor ID", "Name", "Blood Group", "Age", "Gender", "Location", "Contact"};

        // Sample donor data (replace this with DB fetch later)
        Object[][] donorData = {
                {"D101", "Ankit Rawat", "A+", 26, "Male", "Dehradun", "9898989898"},
                {"D102", "Priya Sharma", "O-", 31, "Female", "Rishikesh", "9765432100"},
                {"D103", "Mohit Sinha", "B+", 29, "Male", "Mussoorie", "9123456780"},
                {"D104", "Meena Thakur", "AB-", 24, "Female", "Haridwar", "9012345678"}
        };

        tableModel = new DefaultTableModel(donorData, columnNames);
        donorTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(donorTable);

        // Optional: Add search/filter panel here later

        add(new JLabel("List of Donors", SwingConstants.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
