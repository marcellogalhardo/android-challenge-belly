package br.com.marcellogalhardo.mobilelocation.data;

import java.util.Locale;

public class Coordinate {

    private double latitude;
    private double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        Locale locale = Locale.getDefault();
        return String.format(locale, "%.1f,%.1f", latitude, longitude);
    }

}