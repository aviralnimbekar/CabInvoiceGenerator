package com.bridgelabz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {
    final Map<String, ArrayList<Ride>> userDetail = new HashMap<>();

    public void addRide(String userId, Ride[] rides) {
        ArrayList<Ride> rideList = userDetail.get(userId);
        if (rideList == null)
            userDetail.put(userId, new ArrayList<>(Arrays.asList(rides)));
        else {
            userDetail.get(userId)
                      .addAll(Arrays.asList(rides));
        }
    }

    public Ride[] getList(String userId) {
        try {
            return userDetail.get(userId).toArray(new Ride[0]);
        } catch (NullPointerException e) {
            throw new UserIdNotFoundException(UserIdNotFoundException.ExceptionType.NOT_FOUND, "USER ID NOT FOUND!!!");
        }
    }
}
