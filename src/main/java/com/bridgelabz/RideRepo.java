package com.bridgelabz;

import java.util.HashMap;
import java.util.Map;

public class RideRepo {
    final static Map<String, Ride[]> userDetail = new HashMap<>();

    void addToList(String userId, Ride[] rides) {
        userDetail.put(userId, rides);
    }
}
