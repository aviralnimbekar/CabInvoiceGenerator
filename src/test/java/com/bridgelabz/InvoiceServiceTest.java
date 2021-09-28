package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

    @Mock
    RideRepository rideRepository;

    @InjectMocks
    InvoiceService invoiceService;

    Ride[] rides;
    String userId;

    @BeforeEach
    void toCreateInstanceOfCabInvoiceGenBeforeRunningTests() {
        userId = "a@b.com";
        rides = new Ride[]{
                new Ride(RideCategory.NORMAL, 2.0, 5),
                new Ride(RideCategory.NORMAL, 0.1, 1),
                new Ride(RideCategory.NORMAL, 1.0, 5),
        };
    }

    @Test
    void givenMultipleDistanceAndTime_WhenCalculated_ShouldReturnInvoiceSummary() {
        InvoiceSummary actualSummary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3, 45);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }

    @Test
    void givenUserIdAndRides_ShouldReturnInvoiceSummaryOfTheUserId() {
        invoiceService.addRide(userId, rides);
        when(rideRepository.getList(userId)).thenReturn(rides);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(3, 45);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }

    @Test
    void givenExistingUserIdAndRide_ShouldReturnUpdatedInvoiceSummary() {
        invoiceService.addRide(userId, rides);
        Ride[] newRide = {new Ride(RideCategory.NORMAL, 0.1, 1)};
        invoiceService.addRide(userId, newRide);
        rides = new Ride[]{
                new Ride(RideCategory.NORMAL, 2.0, 5),
                new Ride(RideCategory.NORMAL, 0.1, 1),
                new Ride(RideCategory.NORMAL, 1.0, 5),
                new Ride(RideCategory.NORMAL, 0.1, 1)
        };
        when(rideRepository.getList(userId)).thenReturn(rides);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(4, 50);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }

    @Test
    void givenExistingUserIdAndMultipleRides_ShouldReturnUpdatedInvoiceSummary() {
        invoiceService.addRide(userId, rides);
        Ride[] newRide1 = {new Ride(RideCategory.NORMAL, 0.1, 1)};
        invoiceService.addRide(userId, newRide1);
        rides = new Ride[]{
                new Ride(RideCategory.NORMAL, 2.0, 5),
                new Ride(RideCategory.NORMAL, 0.1, 1),
                new Ride(RideCategory.NORMAL, 1.0, 5),
                new Ride(RideCategory.NORMAL, 0.1, 1)
        };
        Ride[] newRide2 = {new Ride(RideCategory.NORMAL, 1.0, 5)};
        invoiceService.addRide(userId, newRide2);
        rides = new Ride[]{
                new Ride(RideCategory.NORMAL, 2.0, 5),
                new Ride(RideCategory.NORMAL, 0.1, 1),
                new Ride(RideCategory.NORMAL, 1.0, 5),
                new Ride(RideCategory.NORMAL, 0.1, 1),
                new Ride(RideCategory.NORMAL, 1.0, 5)
        };
        when(rideRepository.getList(userId)).thenReturn(rides);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(5, 65);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }

    @Test
    void givenUserId_WhenNonExisting_ShouldReturnUserIdNotFoundException() {
        try {
            when(rideRepository.getList(userId)).thenThrow(new UserIdNotFoundException(UserIdNotFoundException.ExceptionType.NOT_FOUND, "USER ID NOT FOUND!!!"));
            InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        } catch (UserIdNotFoundException e) {
            Assertions.assertEquals(UserIdNotFoundException.ExceptionType.NOT_FOUND, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    void givenDistanceAndTime_WhenCalculatedAsForPremiumRide_ShouldReturnInvoiceSummary() {
        rides = new Ride[]{
                new Ride(RideCategory.PREMIUM, 2.0, 5),
                new Ride(RideCategory.PREMIUM, 0.1, 1),
                new Ride(RideCategory.PREMIUM, 1.0, 5),
        };
        invoiceService.addRide(userId, rides);
        when(rideRepository.getList(userId)).thenReturn(rides);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(3, 85);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }

    @Test
    public void givenDistanceAndTime_WhenCalculatedAsPerCategory_ShouldReturnInvoiceSummary() {
        String userId = "a@b.com";
        rides = new Ride[]{
                new Ride(RideCategory.NORMAL, 2.0, 5),
                new Ride(RideCategory.PREMIUM, 0.1, 1),
                new Ride(RideCategory.NORMAL, 1.0, 5),
                new Ride(RideCategory.PREMIUM, 1.0, 5),
        };
        invoiceService.addRide(userId, rides);
        when(rideRepository.getList(userId)).thenReturn(rides);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(4, 85);
        Assertions.assertEquals(expectedSummary, actualSummary);
    }
}