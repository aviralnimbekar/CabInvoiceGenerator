package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class InvoiceServiceTest {
    InvoiceService invoiceService;
    ArrayList<Ride> rides = new ArrayList<>();

    @BeforeEach
    void toCreateInstanceOfCabInvoiceGenBeforeRunningTests() {
        invoiceService = new InvoiceService();
        rides.add(new Ride(2.0, 5));
        rides.add(new Ride(0.1, 1));
        rides.add(new Ride(1.0, 5));
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
        InvoiceSummary actualSummary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3, 45);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }

    @Test
    void givenUserIdAndRides_ShouldReturnInvoiceSummaryOfTheUserId() {
        String userId = "a@b.com";
        Ride newRide = new Ride(1, 5);
        InvoiceSummary actualSummary = invoiceService.calculateFare(userId, rides, newRide);
        InvoiceSummary expectedSummary = new InvoiceSummary(4, 60);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }

    @Test
    void givenExistingUserIdAndRide_ShouldReturnInvoiceSummaryOfTheUserId() {
        String userId = "a@b.com";
        invoiceService.putInMap(userId, rides);
        Ride newRide = new Ride(1, 5);
        InvoiceSummary actualSummary = invoiceService.calculateFare(userId, rides, newRide);
        InvoiceSummary expectedSummary = new InvoiceSummary(4, 60);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }
}