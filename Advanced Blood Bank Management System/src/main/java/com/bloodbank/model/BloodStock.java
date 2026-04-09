package com.bloodbank.model;

import java.util.HashMap;
import java.util.Map;

public class BloodStock {
    private final Map<String, Integer> stock;

    public BloodStock() {
        stock = new HashMap<>();
        initializeStock();
    }

    private void initializeStock() {
        // Initialize with all common blood types
        String[] types = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        for (String type : types) {
            stock.put(type, 0);  // Start with 0 units
        }
    }

    public void addUnits(String bloodType, int units) {
        stock.put(bloodType, stock.getOrDefault(bloodType, 0) + units);
    }

    public boolean removeUnits(String bloodType, int units) {
        int current = stock.getOrDefault(bloodType, 0);
        if (current >= units) {
            stock.put(bloodType, current - units);
            return true;
        }
        return false;
    }

    public int getUnits(String bloodType) {
        return stock.getOrDefault(bloodType, 0);
    }

    public Map<String, Integer> getAllStock() {
        return stock;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Blood Stock:\n");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(" units\n");
        }
        return sb.toString();
    }
}
