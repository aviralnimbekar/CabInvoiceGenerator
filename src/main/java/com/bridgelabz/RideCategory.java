package com.bridgelabz;

public enum RideCategory {
    NORMAL(10.0, 1.0, 5.0),
    PREMIUM(15.0, 2.0, 20.0);

    private final double costPerKm;
    private final double costPerMint;
    private final double minimumFare;

    RideCategory(double costPerKm, double costPerMint, double minimumFare) {
        this.costPerKm = costPerKm;
        this.costPerMint = costPerMint;
        this.minimumFare = minimumFare;
    }

    public double calculateFare(Ride ride) {
        double fare = ride.distance * costPerKm + ride.time * costPerMint;
        return Math.max(fare, minimumFare);
    }
}
