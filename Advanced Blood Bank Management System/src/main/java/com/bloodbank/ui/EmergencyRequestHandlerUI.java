package com.bloodbank.ui;

import com.bloodbank.model.EmergencyRequest;
import com.bloodbank.service.EmergencyRequestHandler;
import com.bloodbank.service.InventoryService;
import com.bloodbank.util.CompatibilityChecker;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.PriorityQueue;

public class EmergencyRequestHandlerUI extends JFrame {

    private final EmergencyRequestHandler manager;
    private final InventoryService inventoryService;
    private final JTextArea requestDisplay;

    public EmergencyRequestHandlerUI(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.manager = EmergencyRequestHandler.getInstance();

        setTitle("Emergency Request Handler");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Emergency Request Queue", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        requestDisplay = new JTextArea();
        requestDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(requestDisplay);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnHandle = new JButton("Handle Next Fulfillable Request");
        add(btnHandle, BorderLayout.SOUTH);

        btnHandle.addActionListener(e -> handleTopRequest());

        refreshDisplay();
        setVisible(true);
    }

    private void refreshDisplay() {
        PriorityQueue<EmergencyRequest> queue = manager.getQueue();
        requestDisplay.setText("");

        if (queue.isEmpty()) {
            requestDisplay.setText("No emergency requests at the moment.");
        } else {
            int i = 1;
            for (EmergencyRequest request : queue) {
                requestDisplay.append(i++ + ". " + request.toString() + "\n\n");
            }
        }
    }

    private void handleTopRequest() {
        PriorityQueue<EmergencyRequest> queue = new PriorityQueue<>(manager.getQueue()); // Copy to avoid modifying original while scanning

        if (queue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No requests to handle.");
            return;
        }

        boolean fulfilled = false;
        EmergencyRequest toFulfill = null;
        StringBuilder compatibleDetails = new StringBuilder();

        while (!queue.isEmpty()) {
            EmergencyRequest current = queue.poll();
            String recipientType = current.getRequiredBloodType();
            int neededUnits = current.getUnits();

            List<String> compatibleDonors = CompatibilityChecker.getCompatibleDonors(recipientType);
            compatibleDetails.setLength(0); // Clear from previous attempt

            for (String donorType : compatibleDonors) {
                int available = inventoryService.getUnits(donorType);
                if (available >= neededUnits) {
                    inventoryService.deductUnits(donorType, neededUnits); // Assume this method exists
                    compatibleDetails.append("Matched with donor type: ").append(donorType)
                            .append(" (").append(available).append(" units available)\n");
                    toFulfill = current;
                    fulfilled = true;
                    break;
                } else if (available > 0) {
                    compatibleDetails.append("Partial match: ").append(donorType)
                            .append(" (only ").append(available).append(" units available)\n");
                }
            }

            if (fulfilled) break;
        }

        if (fulfilled && toFulfill != null) {
            manager.getQueue().remove(toFulfill); // Remove from actual queue
            JOptionPane.showMessageDialog(this,
                    "Request fulfilled successfully:\n" + toFulfill.toString() + "\n\n" +
                            compatibleDetails.toString(),
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "No requests could be fulfilled at the moment.\n\n" +
                            "Either stock is empty or not enough compatible units.",
                    "Unable to Fulfill", JOptionPane.WARNING_MESSAGE);
        }

        refreshDisplay();
    }
}
