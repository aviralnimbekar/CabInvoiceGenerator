package com.bridgelabz;

import java.util.ArrayList;

import static com.bridgelabz.RideRepo.userDetail;

public class InvoiceService {

    private static final double COST_PER_KM = 10.0;
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_FARE = 5.0;


    public double calculateFare(double distance, int time) {
        double fare = COST_PER_KM * distance + COST_PER_TIME * time;
        return Math.max(fare, MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(ArrayList<Ride> rides) {
        double totalFare = rides.stream()
                                .mapToDouble(ride -> calculateFare(ride.distance, ride.time))
                                .sum();
        return new InvoiceSummary(rides.size(), totalFare);
    }

    public InvoiceSummary calculateFare(String userId, ArrayList<Ride> rideList, Ride newRide) {
        if (userDetail.containsKey(userId))
            rideList = userDetail.get(userId);
        rideList.add(newRide);
        userDetail.put(userId, rideList);
        return calculateFare(rideList);
    }

    public void putInMap(String userId, ArrayList<Ride> rides) {
        userDetail.put(userId, rides);
    }
}
