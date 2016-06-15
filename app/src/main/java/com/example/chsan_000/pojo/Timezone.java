package com.example.chsan_000.pojo;

public class Timezone {
    private String time;

    private String countryName;

    private String sunset;

    private String rawOffset;

    private String dstOffset;

    private String countryCode;

    private String gmtOffset;

    private String lng;

    private String sunrise;

    private String lat;

    private String timezoneId;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getRawOffset() {
        return rawOffset;
    }

    public void setRawOffset(String rawOffset) {
        this.rawOffset = rawOffset;
    }

    public String getDstOffset() {
        return dstOffset;
    }

    public void setDstOffset(String dstOffset) {
        this.dstOffset = dstOffset;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(String gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    @Override
    public String toString() {
        return "ClassPojo [time = " + time + ", countryName = " + countryName + ", sunset = " + sunset + ", rawOffset = " + rawOffset + ", dstOffset = " + dstOffset + ", countryCode = " + countryCode + ", gmtOffset = " + gmtOffset + ", lng = " + lng + ", sunrise = " + sunrise + ", lat = " + lat + ", timezoneId = " + timezoneId + "]";
    }
}