package com.juniordesign.unleashedpotential.canineconcierge;

/**
 * Created by Griffin on 9/10/2017.
 */

/**
 * User class
 * Base of both dog owners and pack leaders
 */
public class User {

    public String email;
    private String firstName;
    private String lastName;
    private String zipCode;
    private String state;
    private String phoneNumber;
    private String address1;
    private String address2;
    private String city;

    /**
     * Empty Constructor
     */
    public User() {}

    /**
     * User constructor with all variables
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
    public User(String email, String firstName, String lastName, String zipCode, String state, String phoneNumber, String address1, String address2, String city) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipCode = zipCode;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
    }

    /**
     * User toString
     * @return String
     */
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", zipCode=" + zipCode +
                ", state=" + state +
                ", phoneNumber=" + phoneNumber +
                ", address1=" + address1 +
                ", address2=" + address2 +
                ", city=" + city +
                '}';
    }

    /**
     * User constructor with only email
     * @param email String
     */
    public User(String email) {
        this.email = email;
    }

    /**
     * Get email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get firstName
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set firstName
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get lastName
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set lastName
     * @param lastName String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get zipCode
     * @return String
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Set zipCode
     * @param zipCode String
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Get state
     * @return String
     */
    public String getState() {
        return state;
    }

    /**
     * Set state
     * @param state String
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Get phoneNumber
     * @return String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set phoneNumber
     * @param phoneNumber String
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get address1
     * @return String
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Set address1
     * @param address1 String
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * Get address2
     * @return String
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Set address2
     * @param address2 String
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * Get city
     * @return String
     */
    public String getCity() {
        return city;
    }

    /**
     * Set city
     * @param city String
     */
    public void setCity(String city) {
        this.city = city;
    }
}