package com.juniordesign.unleashedpotential.canineconcierge;

/**
 * Created by chris on 10/10/2017.
 */

/**
 * Hour
 *
 * List of hours saved to pack leader objects by day
 * Indicates available hours for dog owners to schedule walks
 */
public class Hour {

    String code = null; //0-23
    String name = null; //"10am - 11am"
    boolean selected = false;

    /**
     * Hour Constructor
     *
     * @param code
     * @param name
     * @param selected
     */
    public Hour(String code, String name, boolean selected) {
        super();
        this.code = code;
        this.name = name;
        this.selected = selected;
    }

    /**
     * Prints details of Hour object
     * @return String of Hour info
     */
    public String toString() {
        return "Hour{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", selected=" + selected +
                '}';
    }

    /**
     * get Hour's code (0-23)
     * @return String number
     */
    public String getCode() {
        return code;
    }

    /**
     * set Hour's code
     * @param code String number
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * get Hour's name ("10am - 11am")
     * @return String description of hour
     */
    public String getName() {
        return name;
    }

    /**
     * set Hour's name
     * @param name String description
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Check if hour is selected
     * @return boolean of selected var
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Set boolean "selected" of hour
     * @param selected boolean value
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}