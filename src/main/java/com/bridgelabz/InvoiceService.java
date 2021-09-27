package com.bridgelabz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InvoiceService {

    private static final double COST_PER_KM = 10.0;
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_FARE = 5.0;
    private final Map<String, Ride[]> userDetail = new HashMap<>();

    public double calculateFare(double distance, int time) {
        double fare = COST_PER_KM * distance + COST_PER_TIME * time;
        return Math.max(fare, MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = Arrays.stream(rides)
                .mapToDouble(ride -> calculateFare(ride.distance, ride.time))
                .sum();
        return new InvoiceSummary(rides.length, totalFare);
    }

    public InvoiceSummary calculateFare(String userId, Ride[] rides) {
        addToList(userId, rides);
        Ride[] ridesOfId = userDetail.get(userId);
        return calculateFare(ridesOfId);
    }

    public void addToList(String userId, Ride[] rides) {
        userDetail.put(userId, rides);
    }
}