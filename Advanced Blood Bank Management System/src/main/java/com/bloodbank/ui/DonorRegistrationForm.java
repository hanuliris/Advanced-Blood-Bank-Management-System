package com.bloodbank.ui;

import com.bloodbank.model.Donor;
import com.bloodbank.service.DonorService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DonorRegistrationForm extends JFrame {
    private JTextField nameField, ageField, bloodField, contactField, cityField;
    private JButton submitButton;

    public DonorRegistrationForm() {
        setTitle("Donor Registration");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 100, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 30, 200, 20);
        add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(30, 60, 100, 20);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(150, 60, 200, 20);
        add(ageField);

        JLabel bloodLabel = new JLabel("Blood Group:");
        bloodLabel.setBounds(30, 90, 100, 20);
        add(bloodLabel);

        bloodField = new JTextField();
        bloodField.setBounds(150, 90, 200, 20);
        add(bloodField);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(30, 120, 100, 20);
        add(contactLabel);

        contactField = new JTextField();
        contactField.setBounds(150, 120, 200, 20);
        add(contactField);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(30, 150, 100, 20);
        add(cityLabel);

        cityField = new JTextField();
        cityField.setBounds(150, 150, 200, 20);
        add(cityField);

        submitButton = new JButton("Register Donor");
        submitButton.setBounds(120, 200, 150, 30);
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Donor donor = new Donor(0,
                        nameField.getText(),
                        Integer.parseInt(ageField.getText()),
                        bloodField.getText(),
                        contactField.getText(),
                        cityField.getText()
                );

                DonorService service = new DonorService();
                boolean success = service.registerDonor(donor);
                JOptionPane.showMessageDialog(null, success ? "Donor Registered" : "Error Occurred");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new DonorRegistrationForm();
    }
}
