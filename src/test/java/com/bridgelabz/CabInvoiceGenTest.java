package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CabInvoiceGenTest {

    @Test
    void givenDistanceAndTime_ShouldReturnTotalFare() {
        int time = 5;
        double distance = 2.0;
        CabInvoiceGen cabInvoiceGen = new CabInvoiceGen();
        double fare = cabInvoiceGen.fareCalculator(distance, time);
        Assertions.assertEquals(25, fare);
    }
}
