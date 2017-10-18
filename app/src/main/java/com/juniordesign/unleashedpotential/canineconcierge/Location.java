package com.juniordesign.unleashedpotential.canineconcierge;

/**
 * Created by Griffin on 10/1/2017.
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

    public Location() {}
    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getBearing() {
        return bearing;
    }

    public void setBearing(int bearing) {
        this.bearing = bearing;
    }

    public int getBearingAccuracyDegrees() {
        return bearingAccuracyDegrees;
    }

    public void setBearingAccuracyDegrees(int bearingAccuracyDegrees) {
        this.bearingAccuracyDegrees = bearingAccuracyDegrees;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getElapsedRealtimeNanos() {
        return elapsedRealtimeNanos;
    }

    public void setElapsedRealtimeNanos(int elapsedRealtimeNanos) {
        this.elapsedRealtimeNanos = elapsedRealtimeNanos;
    }

    public boolean isFromMockProvider() {
        return fromMockProvider;
    }

    public void setFromMockProvider(boolean fromMockProvider) {
        this.fromMockProvider = fromMockProvider;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeedAccuracyMetersPerSecond() {
        return speedAccuracyMetersPerSecond;
    }

    public void setSpeedAccuracyMetersPerSecond(int speedAccuracyMetersPerSecond) {
        this.speedAccuracyMetersPerSecond = speedAccuracyMetersPerSecond;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getVerticalAccuracyMeters() {
        return verticalAccuracyMeters;
    }

    public void setVerticalAccuracyMeters(int verticalAccuracyMeters) {
        this.verticalAccuracyMeters = verticalAccuracyMeters;
    }

    public Location(String provider, int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.provider = provider;
    }

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
}
