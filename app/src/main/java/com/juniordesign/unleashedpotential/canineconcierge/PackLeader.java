package com.juniordesign.unleashedpotential.canineconcierge;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Brivas on 9/29/2017.
 */

public class PackLeader extends User {
    private HashMap availableHrs;
    private Map unavailableHrs;
    public PackLeader(String email, String firstName, String lastName, String zipCode, String state, String phoneNumber, String address1,
                            String address2, String city) {
        super(email, firstName, lastName, zipCode, state, phoneNumber, address1, address2, city);
        availableHrs = new HashMap<String, ArrayList<Integer>>();
        availableHrs.put("Monday", null);
        availableHrs.put("Tuesday", null);
        availableHrs.put("Wednesday", null);
        availableHrs.put("Thursday", null);
        availableHrs.put("Friday", null);
        availableHrs.put("Saturday", null);
        availableHrs.put("Sunday", null);

    }
    public HashMap<String, ArrayList<Integer>> getAvailHrs() {
        return availableHrs;
    }
    public void addHrs(String day, ArrayList<Integer> list) {
        availableHrs.put(day, list);
    }
}
