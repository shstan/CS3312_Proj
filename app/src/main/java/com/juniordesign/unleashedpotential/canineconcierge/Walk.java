package com.juniordesign.unleashedpotential.canineconcierge;

import java.util.Date;

/**
 * Created by Stan Park on 2017-09-20.
 */

/**
 * Walk class
 * Created when a user schedules and pays for walk
 */
public class Walk {

    private String walkerID;
    private String clientID;
    private String dogName;
    private Date startTime;
    private Date endTime;
    private Location startingLocation;
    private Location endLocation;
    private double distance;
    private boolean completed;
    private String walkID;

    /**
     * Empty Constructor
     */
    public Walk() {}

    /**
     * Walk constructor with all variables
     * @param walkerID
     * @param clientID
     * @param dogName
     * @param startTime
     * @param endTime
     * @param startingLocation
     * @param endLocation
     * @param distance
     */
    public Walk(String walkerID, String clientID, String dogName, Date startTime, Date endTime, Location startingLocation, Location endLocation, double distance) {
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

    /**
     * Get walkerID
     * @return String
     */
    public String getWalkerID() {
        return walkerID;
    }

    /**
     * Set walkerID - String concat of first and last name of pack leader
     * @param walkerID String
     */
    public void setWalkerID(String walkerID) {
        this.walkerID = walkerID;
    }

    /**
     * Get clientID - uID of dog owner
     * @return String
     */
    public String getClientID() {
        return clientID;
    }

    /**
     * Set clientID
     * @param clientID String
     */
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    /**
     * Get dogName
     * @return String
     */
    public String getDogName() {
        return dogName;
    }

    /**
     * Set dogName
     * @param dogName String
     */
    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    /**
     * Get startTime
     * @return Date
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Set startTime
     * @param startTime Date
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Get endTime
     * @return Date
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Set endTime
     * @param endTime Date
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Get startingLocation
     * @return Location
     */
    public Location getStartingLocation() {
        return startingLocation;
    }

    /**
     * Set startingLocation
     * @param startingLocation Location
     */
    public void setStartingLocation(Location startingLocation) {
        this.startingLocation = startingLocation;
    }

    /**
     * Get endLocation
     * @return Location
     */
    public Location getEndLocation() {
        return endLocation;
    }

    /**
     * Set endLocation
     * @param endLocation Location
     */
    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    /**
     * Get distance
     * @return double
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Set distance
     * @param distance double
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Get is completed
     * @return boolean
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Set completed
     * @param completed boolean
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Get walkID
     * @return String
     */
    public String getWalkID() {
        return walkID;
    }

    /**
     * Set WalkID
     * @param walkID String
     */
    public void setWalkID(String walkID) {
        this.walkID = walkID;
    }
}