package com.bridgelabz;

public class CabInvoiceGen {

    private static final double COST_PER_KM = 10.0;
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_FARE = 5.0;

    public double calculateFare(double distance, int time) {
        double fare = COST_PER_KM * distance + COST_PER_TIME * time;
        return Math.max(fare, MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        int numOfRides = 0;
        double totalFare = 0.0;
        for (Ride ride : rides) {
            numOfRides++;
            totalFare += calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(numOfRides, totalFare);
    }
}
