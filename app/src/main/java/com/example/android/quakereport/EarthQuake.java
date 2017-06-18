package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jordan on 5/27/2017.
 */

public class EarthQuake {
    private double magnitude;
    private String location;
    private long date;
    private String dateToDisplay = "Jan 1, 1970";
    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {

        return dateToDisplay;
    }


    public EarthQuake(String location){
        this.magnitude = 3;
        this.location = location;
        this.date = 0;

    }
    public EarthQuake(double magnitude, String location, long date) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
        Date dateObject = new Date(this.date);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        dateToDisplay = dateFormatter.format(dateObject);
    }



}
