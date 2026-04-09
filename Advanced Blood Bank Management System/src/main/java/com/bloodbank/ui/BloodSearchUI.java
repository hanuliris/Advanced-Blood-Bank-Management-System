package com.bloodbank.ui;

import com.bloodbank.model.Donor;
import com.bloodbank.util.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Vector;

public class BloodSearchUI extends JFrame {

    private JComboBox<String> bloodGroupBox;
    private JTextField cityField;
    private DefaultTableModel tableModel;

    public BloodSearchUI() {
        setTitle("Search for Donors");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for input
        JPanel searchPanel = new JPanel(new FlowLayout());

        searchPanel.add(new JLabel("Blood Group:"));
        bloodGroupBox = new JComboBox<>(new String[]{
                "Select", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        });
        searchPanel.add(bloodGroupBox);

        searchPanel.add(new JLabel("City (optional):"));
        cityField = new JTextField(10);
        searchPanel.add(cityField);

        JButton searchBtn = new JButton("Search");
        searchPanel.add(searchBtn);

        add(searchPanel, BorderLayout.NORTH);

        // Table for results
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Name", "Age", "Gender", "Blood Group", "City", "Contact"}, 0
        );
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Action listener
        searchBtn.addActionListener((ActionEvent e) -> performSearch());

        setVisible(true);
    }

    private void performSearch() {
        String bloodGroup = (String) bloodGroupBox.getSelectedItem();
        String city = cityField.getText().trim();

        if ("Select".equals(bloodGroup)) {
            JOptionPane.showMessageDialog(this, "Please select a valid blood group.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            StringBuilder query = new StringBuilder("SELECT * FROM donors WHERE blood_group = ?");
            if (!city.isEmpty()) {
                query.append(" AND city LIKE ?");
            }

            PreparedStatement stmt = conn.prepareStatement(query.toString());
            stmt.setString(1, bloodGroup);
            if (!city.isEmpty()) {
                stmt.setString(2, "%" + city + "%");
            }

            ResultSet rs = stmt.executeQuery();
            tableModel.setRowCount(0); // clear old results

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("id"));
                row.add(rs.getString("name"));
                row.add(rs.getInt("age"));
                row.add(rs.getString("gender"));
                row.add(rs.getString("blood_group"));
                row.add(rs.getString("city"));
                row.add(rs.getString("contact"));
                tableModel.addRow(row);
            }

            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No matching donors found.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error fetching donors:\n" + ex.getMessage());
        }
    }
}
