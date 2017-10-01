package com.juniordesign.unleashedpotential.canineconcierge;

import android.location.Location;
import java.util.Calendar;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Stan Park on 2017-09-20.
 */

public class Walk {


    private String walkerID;
    private String clientID;
    private String dogName;
    private Calendar startTime;
    private Calendar endTime;
    private Location startingLocation;
    private Location endLocation;
    private double distance;
    private boolean completed;

    @Override
    public String toString() {
        return "Walk{" +
                "walkerID='" + walkerID + '\'' +
                ", clientID='" + clientID + '\'' +
                ", dogName='" + dogName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", startingLocation=" + startingLocation +
                ", endLocation=" + endLocation +
                ", distance=" + distance +
                '}';
    }

    public Walk(String walkerID, String clientID, String dogName, Calendar startTime, Calendar endTime, Location startingLocation, Location endLocation, double distance) {
        this.walkerID = walkerID;
        this.clientID = clientID;
        this.dogName = dogName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startingLocation = startingLocation;
        this.endLocation = endLocation;
        this.distance = distance;
        this.completed = false;
    }

    public Walk() {
        // Default constructor
    }

    public String getWalkerID() {
        return walkerID;
    }

    public void setWalkerID(String walkerID) {
        this.walkerID = walkerID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public Location getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(Location startingLocation) {
        this.startingLocation = startingLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
