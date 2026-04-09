package com.bloodbank.model;

public class EmergencyRequest implements Comparable<EmergencyRequest> {
    private String patientName;
    private String bloodGroup;
    private int urgencyLevel;

    public EmergencyRequest(String patientName, String bloodGroup, int urgencyLevel) {
        this.patientName = patientName;
        this.bloodGroup = bloodGroup;
        this.urgencyLevel = urgencyLevel;
    }

    // Existing getters
    public String getPatientName() { return patientName; }
    public String getBloodGroup() { return bloodGroup; }
    public int getUrgencyLevel() { return urgencyLevel; }

    // Added methods for compatibility with EmergencyRequestHandlerUI and InventoryService
    public String getRequiredBloodType() {
        return bloodGroup;
    }

    public int getUnits() {
        return urgencyLevel; // Assuming 'urgencyLevel' represents required units
    }

    @Override
    public int compareTo(EmergencyRequest other) {
        return Integer.compare(other.urgencyLevel, this.urgencyLevel); // higher urgency first
    }

    @Override
    public String toString() {
        return "Patient: " + patientName +
                ", Blood Group: " + bloodGroup +
                ", Urgency Level: " + urgencyLevel;
    }
}
