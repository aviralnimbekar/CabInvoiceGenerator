package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CabInvoiceGenTest {

    @Test
    void givenDistanceAndTime_ShouldReturnTotalFare() {
        CabInvoiceGen cabInvoiceGen = new CabInvoiceGen();
        double distance = 2.0;
        int time = 5;
        double fare = cabInvoiceGen.calculateFare(distance,time);
        Assertions.assertEquals(25, fare);
    }

    @Test
    void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        CabInvoiceGen cabInvoiceGen = new CabInvoiceGen();
        double distance = 0.1;
        int time = 1;
        double minFare = cabInvoiceGen.calculateFare(distance,time);
        Assertions.assertEquals(5, minFare);
    }
}
