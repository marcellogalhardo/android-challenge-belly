package br.com.marcellogalhardo.mobilelocation.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Business {

    private String name;

    @SerializedName("image_url")
    private String thumb;

    private double rating;

    @SerializedName("is_closed")
    private boolean isClosed;

    private Location location;

    private List<List<String>> categories;

    private double distance;

    public Business(String name, String thumb, double rating, boolean isClosed, Location location, List<List<String>> categories) {
        this.name = name;
        this.thumb = thumb;
        this.rating = rating;
        this.isClosed = isClosed;
        this.location = location;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public String getThumb() {
        return thumb;
    }

    public double getRating() {
        return rating;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public Location getLocation() {
        return location;
    }

    public Coordinate getCoordinate() {
        return location.getCoordinate();
    }

    public List<List<String>> getCategories() {
        return categories;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}