package com.example.chsan_000.pojo;

public class Postalcodes {
    private String adminName2;

    private String postalcode;

    private String adminName3;

    private String adminCode1;

    private String countryCode;

    private String placeName;

    private double lng;

    private double lat;

    private String adminName1;

    public String getAdminName2() {
        return adminName2;
    }

    public void setAdminName2(String adminName2) {
        this.adminName2 = adminName2;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getAdminName3() {
        return adminName3;
    }

    public void setAdminName3(String adminName3) {
        this.adminName3 = adminName3;
    }

    public String getAdminCode1() {
        return adminCode1;
    }

    public void setAdminCode1(String adminCode1) {
        this.adminCode1 = adminCode1;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getAdminName1() {
        return adminName1;
    }

    public void setAdminName1(String adminName1) {
        this.adminName1 = adminName1;
    }

    @Override
    public String toString() {
        return "ClassPojo [adminName2 = " + adminName2 + ", postalcode = " + postalcode + ", adminName3 = " + adminName3 + ", adminCode1 = " + adminCode1 + ", countryCode = " + countryCode + ", placeName = " + placeName + ", lng = " + lng + ", lat = " + lat + ", adminName1 = " + adminName1 + "]";
    }
}