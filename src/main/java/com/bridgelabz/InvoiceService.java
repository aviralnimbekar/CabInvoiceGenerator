package com.bridgelabz;

import java.util.Arrays;

public class InvoiceService {
    private final RideRepository rideRepository = new RideRepository();

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = Arrays.stream(rides)
                              .mapToDouble(ride -> ride.category.calculateFare(ride))
                              .sum();
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRide(String userId, Ride[] rides) {
        rideRepository.addRide(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        Ride[] rides = rideRepository.getList(userId);
        return this.calculateFare(rides);
    }
}
