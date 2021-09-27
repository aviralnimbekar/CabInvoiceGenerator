package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InvoiceServiceTest {
    InvoiceService invoiceService;

    @BeforeEach
    void toCreateInstanceOfCabInvoiceGenBeforeRunningTests() {
        invoiceService = new InvoiceService();
    }

    @Test
    void givenDistanceAndTime_WhenCalculated_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(distance, time);
        Assertions.assertEquals(25, fare);
    }

    @Test
    void givenLessDistanceAndTime_WhenCalculatedAndCompared_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double minFare = invoiceService.calculateFare(distance, time);
        Assertions.assertEquals(5, minFare);
    }

    @Test
    void givenMultipleDistanceAndTime_WhenCalculated_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0, 5),
                        new Ride(0.1, 1),
                        new Ride(1.0, 5),
        };
        InvoiceSummary actualSummary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3, 45);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }

    @Test
    void givenUserIdAndRides_ShouldReturnInvoiceSummaryOfTheUserId() {
        String userId = "a@b.com";
        Ride[] rides = {new Ride(2.0, 5),
                        new Ride(0.1, 1),
                        new Ride(1.0, 5),
        };
        InvoiceSummary actualSummary = invoiceService.calculateFare(userId, rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3, 45);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }
}
