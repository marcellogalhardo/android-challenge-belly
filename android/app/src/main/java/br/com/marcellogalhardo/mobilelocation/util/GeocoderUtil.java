package br.com.marcellogalhardo.mobilelocation.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.marcellogalhardo.mobilelocation.data.Coordinate;

@Singleton
public class GeocoderUtil {

    private static final double EARTH_RADIUS = 3961;

    @Inject
    GeocoderUtil() {
    }

    public double calculateDistance(Coordinate c1, Coordinate c2) {
        double lat1 = toRadians(c1.getLatitude());
        double lon1 = toRadians(c1.getLongitude());
        double lat2 = toRadians(c2.getLatitude());
        double lon2 = toRadians(c2.getLongitude());
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    private static double toRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    public String getYourLocality(Context context, Location location) {
        String locality = "";
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses.size() > 0 && addresses.get(0).getLocality() != null) {
                locality = addresses.get(0).getLocality();
                if (addresses.get(0).getAdminArea() != null) {
                    locality += ", " + addresses.get(0).getAdminArea();
                }
            }
        } catch (IOException exception) {
            locality = "";
        }
        return locality;
    }
}
