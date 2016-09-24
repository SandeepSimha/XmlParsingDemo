package com.company.sandeep.pojo;

import java.util.List;

public class MyResponse {

    private List<Postalcodes> postalcodes;

    public List<Postalcodes> getPostalcodes() {
        return postalcodes;
    }

    public void setPostalcodes(List<Postalcodes> postalcodes) {
        this.postalcodes = postalcodes;
    }

    @Override
    public String toString() {
        return "ClassPojo [postalcodes = " + postalcodes + "]";
    }
}