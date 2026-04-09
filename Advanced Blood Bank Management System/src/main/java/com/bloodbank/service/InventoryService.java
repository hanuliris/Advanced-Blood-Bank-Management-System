package com.bloodbank.service;

import com.bloodbank.model.BloodStock;
import com.bloodbank.util.CompatibilityChecker;

import java.util.List;

public class InventoryService {

    private final BloodStock bloodStock;

    public InventoryService(BloodStock bloodStock) {
        this.bloodStock = bloodStock;
    }

    // Add blood to stock
    public void donateBlood(String bloodType, int units) {
        bloodStock.addUnits(bloodType, units);
        System.out.println(units + " units added for " + bloodType);
    }

    // Request blood: checks compatible types and tries to fulfill from any of them
    public boolean requestBlood(String recipientType, int requiredUnits) {
        List<String> compatibleDonors = CompatibilityChecker.getCompatibleDonors(recipientType);

        for (String donorType : compatibleDonors) {
            int available = bloodStock.getUnits(donorType);
            if (available >= requiredUnits) {
                bloodStock.removeUnits(donorType, requiredUnits);
                System.out.println("Request fulfilled using " + donorType + " for recipient " + recipientType);
                return true;
            }
        }

        System.out.println("Insufficient stock for recipient type: " + recipientType);
        return false;
    }

    // Check units available
    public int checkAvailability(String bloodType) {
        return bloodStock.getUnits(bloodType);
    }

    // View all stock
    public void displayInventory() {
        System.out.println(bloodStock);
    }

    public int getUnits(String bloodType) {
        return bloodStock.getUnits(bloodType);
    }

    public void deductUnits(String bloodType, int units) {
        bloodStock.removeUnits(bloodType, units);
    }
}
