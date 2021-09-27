package com.bridgelabz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideRepo {
    private final Map<String, ArrayList<Ride>> userDetail = new HashMap<>();

    public List<Ride> getInvoiceList(String userId) {
        ArrayList<Ride> list = userDetail.get(userId);
        return list;
    }
}
