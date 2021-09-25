package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CabInvoiceGenTest {
    CabInvoiceGen cabInvoiceGen;

    @BeforeEach
    void toCreateInstanceOfCabInvoiceGenBeforeRunningTest() {
        cabInvoiceGen = new CabInvoiceGen();
    }

    @Test
    void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = cabInvoiceGen.calculateFare(distance, time);
        Assertions.assertEquals(25, fare);
    }

    @Test
    void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double minFare = cabInvoiceGen.calculateFare(distance, time);
        Assertions.assertEquals(5, minFare);
    }
}
