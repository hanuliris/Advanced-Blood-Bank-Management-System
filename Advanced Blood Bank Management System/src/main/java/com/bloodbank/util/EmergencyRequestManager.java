package com.bloodbank.util;

import com.bloodbank.model.EmergencyRequest;

import java.util.PriorityQueue;

public class EmergencyRequestManager {
    private static final PriorityQueue<EmergencyRequest> requestQueue = new PriorityQueue<>();

    public static void addRequest(EmergencyRequest request) {
        requestQueue.offer(request);
    }

    public static PriorityQueue<EmergencyRequest> getAllRequests() {
        return new PriorityQueue<>(requestQueue); // return a copy
    }
}
