package com.example.android.quakereport;

/**
 * Created by Jordan on 5/27/2017.
 */

public class EarthQuake {
    private double magnitude;
    private String location;
    private String date;

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }


    public EarthQuake(String location){
        this.magnitude = 3;
        this.location = location;
        this.date = "January 1, 1970";
    }
    public EarthQuake(double magnitude, String location, String date) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
    }

}
