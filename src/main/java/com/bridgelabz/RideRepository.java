package com.bridgelabz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {
    final Map<String, ArrayList<Ride>> userDetail = new HashMap<>();

    public void addRide(String userId, Ride[] rides) {
        userDetail.put(userId, new ArrayList<>(Arrays.asList(rides)));
    }

    public ArrayList<Ride> getList(String userId) {
        return userDetail.get(userId);
    }
}
