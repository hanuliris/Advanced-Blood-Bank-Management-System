package com.bloodbank.util;

import com.bloodbank.model.Donor;

import java.util.ArrayList;
import java.util.List;

public class DonorDataManager {

    private static final List<Donor> donorList = new ArrayList<>();

    // Add a donor to the list
    public static void addDonor(Donor donor) {
        donorList.add(donor);
    }

    // Get the list of all donors
    public static List<Donor> getAllDonors() {
        return donorList;
    }

    // Get a donor by name
    public static Donor getDonorByName(String name) {
        return donorList.stream()
                .filter(d -> d.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}

