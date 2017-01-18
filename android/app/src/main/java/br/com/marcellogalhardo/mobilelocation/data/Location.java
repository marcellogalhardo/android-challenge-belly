package br.com.marcellogalhardo.mobilelocation.data;

public class Location {

    public Coordinate coordinate;

    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

}