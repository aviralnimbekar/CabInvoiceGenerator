package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InvoiceServiceTest {
    InvoiceService invoiceService;
    Ride[] rides;

    @BeforeEach
    void toCreateInstanceOfCabInvoiceGenBeforeRunningTests() {
        invoiceService = new InvoiceService();
        rides = new Ride[]{new Ride(2.0, 5),
                new Ride(0.1, 1),
                new Ride(1.0, 5),
        };
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
        invoiceService.addRide(userId, rides);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(3, 45);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }

    @Test
    void givenExistingUserIdAndRide_ShouldReturnUpdatedInvoiceSummary() {
        String userId = "a@b.com";
        invoiceService.addRide(userId, rides);
        Ride[] newRide = {new Ride(0.1, 1)};
        invoiceService.addRide(userId, newRide);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(4, 50);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }

    @Test
    void givenExistingUserIdAndMultipleRides_ShouldReturnUpdatedInvoiceSummary() {
        String userId = "a@b.com";
        invoiceService.addRide(userId, rides);
        Ride[] newRide1 = {new Ride(0.1, 1)};
        invoiceService.addRide(userId, newRide1);
        Ride[] newRide2 = {new Ride(1, 5)};
        invoiceService.addRide(userId, newRide2);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(5, 65);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }

    @Test
    void givenUserId_WhenNonExisting_ShouldReturnUserIdNotFoundException() {
        String userId = "c@d.com";
        try {
            InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        }catch (UserIdNotFoundException e) {
            Assertions.assertEquals(UserIdNotFoundException.ExceptionType.NOT_FOUND, e.type);
            System.out.println(e.getMessage());
        }
    }
}