package com.bridgelabz;

public class CabInvoiceGen {

    private static final double COST_PER_KM = 10.0;
    private static final int COST_PER_MINT = 1;

    public double fareCalculator(double distance, int time) {
        return COST_PER_KM * distance + COST_PER_MINT * time;
    }
}
