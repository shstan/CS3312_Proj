package com.juniordesign.unleashedpotential.canineconcierge;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Brivas on 9/29/2017.
 */

/**
 * Pack Leader Class
 * Extension of User class, adds available hours variable for scheduling walks
 */
public class PackLeader extends User {

    private HashMap availableHrs;
    private Map unavailableHrs;

    /**
     * Empty Constructor
     */
    public PackLeader() {}

    /**
     * Pack Leader Constructor with all user vars
     * @param email
     * @param firstName
     * @param lastName
     * @param zipCode
     * @param state
     * @param phoneNumber
     * @param address1
     * @param address2
     * @param city
     */
    public PackLeader(String email, String firstName, String lastName, String zipCode, String state, String phoneNumber, String address1,
                            String address2, String city) {
        //User constructor
        super(email, firstName, lastName, zipCode, state, phoneNumber, address1, address2, city);

        //Set up availableHrs HashMap
        availableHrs = new HashMap<String, ArrayList<Integer>>();
        availableHrs.put("Monday", null);
        availableHrs.put("Tuesday", null);
        availableHrs.put("Wednesday", null);
        availableHrs.put("Wednesday", null);
        availableHrs.put("Thursday", null);
        availableHrs.put("Friday", null);
        availableHrs.put("Saturday", null);
        availableHrs.put("Sunday", null);

    }

    /**
     * get availableHrs
     * @return HashMap<String,ArrayList<Integer>>
     */
    public HashMap<String, ArrayList<Integer>> getAvailHrs() {
        return availableHrs;
    }

    /**
     * Add avilable hours to availableHrs HashMap
     * @param day String
     * @param list ArrayList<Integer>
     */
    public void addHrs(String day, ArrayList<Integer> list) {
        availableHrs.put(day, list);
    }
}
