package com.bridgelabz;

public class Ride {
    double distance;
    double time;
    RideCategory category;

    public Ride(RideCategory category, double distance, double time) {
        this.category = category;
        this.distance = distance;
        this.time = time;
    }
}
