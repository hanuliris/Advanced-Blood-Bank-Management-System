package com.bloodbank.service;

import com.bloodbank.model.EmergencyRequest;
import java.util.PriorityQueue;

public class EmergencyRequestHandler {
    private static EmergencyRequestHandler instance;
    private PriorityQueue<EmergencyRequest> queue;

    private EmergencyRequestHandler() {
        queue = new PriorityQueue<>();
        // Add some dummy requests
        queue.add(new EmergencyRequest("Amit", "O+", 5));
        queue.add(new EmergencyRequest("Neha", "A-", 8));
        queue.add(new EmergencyRequest("Raj", "B+", 3));
    }

    public static EmergencyRequestHandler getInstance() {
        if (instance == null) {
            instance = new EmergencyRequestHandler();
        }
        return instance;
    }

    public PriorityQueue<EmergencyRequest> getQueue() {
        return queue;
    }

    public EmergencyRequest removeTopRequest() {
        return queue.poll();
    }

    public EmergencyRequest peekTopRequest() {
        return queue.peek();
    }
}
