package com.bignerdranch.android.travelplanner.data;

public class Country {

    private String mCountry;
    private String mCity;
    private String mHotel;
    private String mDate;
    private Boolean mDone;

    public Country(String country, String city, String hotel, String date, Boolean done) {
        mCountry = country;
        mCity = city;
        mHotel = hotel;
        mDate = date;
        mDone = done;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getCity() {
        return mCity;
    }

    public String getHotel() {
        return mHotel;
    }

    public String getDate() {
        return mDate;
    }

    public Boolean getDone() {
        return mDone;
    }

    public void setDone(Boolean done) {
        mDone = done;
    }
}
