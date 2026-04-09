package com.bloodbank.util;

import java.util.*;

public class CompatibilityChecker {

    private static final Map<String, Set<String>> compatibilityMap = new HashMap<>();

    static {
        compatibilityMap.put("O-", Set.of("O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+"));
        compatibilityMap.put("O+", Set.of("O+", "A+", "B+", "AB+"));
        compatibilityMap.put("A-", Set.of("A-", "A+", "AB-", "AB+"));
        compatibilityMap.put("A+", Set.of("A+", "AB+"));
        compatibilityMap.put("B-", Set.of("B-", "B+", "AB-", "AB+"));
        compatibilityMap.put("B+", Set.of("B+", "AB+"));
        compatibilityMap.put("AB-", Set.of("AB-", "AB+"));
        compatibilityMap.put("AB+", Set.of("AB+"));
    }

    // Can donorType donate to recipientType?
    public static boolean isCompatible(String donorType, String recipientType) {
        return compatibilityMap.getOrDefault(donorType, Set.of()).contains(recipientType);
    }

    // Returns a list of compatible donor types for a given recipient type
    public static List<String> getCompatibleDonors(String recipientType) {
        List<String> compatibleDonors = new ArrayList<>();
        for (String donorType : compatibilityMap.keySet()) {
            if (isCompatible(donorType, recipientType)) {
                compatibleDonors.add(donorType);
            }
        }
        return compatibleDonors;
    }
}
