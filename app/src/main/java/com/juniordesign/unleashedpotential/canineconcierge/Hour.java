package com.juniordesign.unleashedpotential.canineconcierge;

/**
 * Created by chris on 10/10/2017.
 */

public class Hour {

    String code = null; //0-23
    String name = null; //"10am - 11am"
    boolean selected = false;

    public Hour(String code, String name, boolean selected) {
        super();
        this.code = code;
        this.name = name;
        this.selected = selected;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}