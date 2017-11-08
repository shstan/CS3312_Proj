package com.juniordesign.unleashedpotential.canineconcierge;

/**
 * Created by Griffin on 10/1/2017.
 */

/**
 * Location class
 * Used in Walk class to describe start and ending location
 */
public class Location {
    private int accuracy;
    private int altitude;
    private int bearing;
    private int bearingAccuracyDegrees;
    private boolean complete;
    private int elapsedRealtimeNanos;
    private boolean fromMockProvider;
    private int latitude;
    private int longitude;
    private String provider;
    private int speed;
    private int speedAccuracyMetersPerSecond;
    private int time;
    private int verticalAccuracyMeters;

    /**
     * Empty constructor
     */
    public Location() {}

    /**
     * Location constructor used in Walk creation
     * @param provider
     * @param latitude
     * @param longitude
     */
    public Location(String provider, int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.provider = provider;
    }

    /**
     * Location constructor including all variables
     */
    public Location(int accuracy, int altitude, int bearing, int bearingAccuracyDegrees, boolean complete, int elapsedRealtimeNanos, boolean fromMockProvider, int latitude, int longitude, String provider, int speed, int speedAccuracyMetersPerSecond, int time, int verticalAccuracyMeters) {
        this.accuracy = accuracy;
        this.altitude = altitude;
        this.bearing = bearing;
        this.bearingAccuracyDegrees = bearingAccuracyDegrees;
        this.complete = complete;
        this.elapsedRealtimeNanos = elapsedRealtimeNanos;
        this.fromMockProvider = fromMockProvider;
        this.latitude = latitude;
        this.longitude = longitude;
        this.provider = provider;
        this.speed = speed;
        this.speedAccuracyMetersPerSecond = speedAccuracyMetersPerSecond;
        this.time = time;
        this.verticalAccuracyMeters = verticalAccuracyMeters;
    }

    /**
     * Get Accuracy
     * @return int accuracy
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * Set accuracy
     * @param accuracy int
     */
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * Get altitude
     * @return int
     */
    public int getAltitude() {
        return altitude;
    }

    /**
     * Set altitude
     * @param altitude int
     */
    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    /**
     * Get bearing
     * @return int
     */
    public int getBearing() {
        return bearing;
    }

    /**
     * Set bearing
     * @param bearing int
     */
    public void setBearing(int bearing) {
        this.bearing = bearing;
    }

    /**
     * Get bearingAccuracyDegrees
     * @return int
     */
    public int getBearingAccuracyDegrees() {
        return bearingAccuracyDegrees;
    }

    /**
     * Set bearingAccuracyDegrees
     * @param bearingAccuracyDegrees int
     */
    public void setBearingAccuracyDegrees(int bearingAccuracyDegrees) {
        this.bearingAccuracyDegrees = bearingAccuracyDegrees;
    }

    /**
     * Get isComplete
     * @return boolean
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * Set Completed
     * @param complete boolean
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    /**
     * Get elapsedRealtimeNanos
     * @return int
     */
    public int getElapsedRealtimeNanos() {
        return elapsedRealtimeNanos;
    }

    /**
     * Set elapsedRealtimeNanos
     * @param elapsedRealtimeNanos int
     */
    public void setElapsedRealtimeNanos(int elapsedRealtimeNanos) {
        this.elapsedRealtimeNanos = elapsedRealtimeNanos;
    }

    /**
     * Get fromMockProvider
     * @return boolean
     */
    public boolean isFromMockProvider() {
        return fromMockProvider;
    }

    /**
     * Set fromMockProvider
     * @param fromMockProvider boolean
     */
    public void setFromMockProvider(boolean fromMockProvider) {
        this.fromMockProvider = fromMockProvider;
    }

    /**
     * Get latitude
     * @return int
     */
    public int getLatitude() {
        return latitude;
    }

    /**
     * Set latitude
     * @param latitude ing
     */
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    /**
     * Get latitude
     * @return int
     */
    public int getLongitude() {
        return longitude;
    }

    /**
     * Set longitude
     * @param longitude int
     */
    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    /**
     * Get provider
     * @return String
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Set provider
     * @param provider String
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * Get speed
     * @return int
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Set speed
     * @param speed int
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Get speedAccuracyMetersPerSecond
     * @return int
     */
    public int getSpeedAccuracyMetersPerSecond() {
        return speedAccuracyMetersPerSecond;
    }

    /**
     * Set speedAccuracy MetersPerSecond
     * @param speedAccuracyMetersPerSecond int
     */
    public void setSpeedAccuracyMetersPerSecond(int speedAccuracyMetersPerSecond) {
        this.speedAccuracyMetersPerSecond = speedAccuracyMetersPerSecond;
    }

    /**
     * Get time
     * @return int
     */
    public int getTime() {
        return time;
    }

    /**
     * Set time
     * @param time int
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Get verticalAccuracyMeters
     * @return int
     */
    public int getVerticalAccuracyMeters() {
        return verticalAccuracyMeters;
    }

    /**
     * Set verticalAccuracyMeters
     * @param verticalAccuracyMeters int
     */
    public void setVerticalAccuracyMeters(int verticalAccuracyMeters) {
        this.verticalAccuracyMeters = verticalAccuracyMeters;
    }
}
