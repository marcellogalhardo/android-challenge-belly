package br.com.marcellogalhardo.mobilelocation.nearby;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.marcellogalhardo.mobilelocation.data.Business;
import br.com.marcellogalhardo.mobilelocation.data.Coordinate;

public class NearByMapHelper {

    public void setupSettings(GoogleMap map) {
        map.setMyLocationEnabled(true);
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setRotateGesturesEnabled(true);
    }

    public void moveCamera(GoogleMap map, Coordinate coordinate) {
        LatLng latLng = getLatLng(coordinate);
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16.0f);
        map.animateCamera(cameraUpdate);
    }

    private LatLng getLatLng(Coordinate coordinate) {
        return new LatLng(coordinate.getLatitude(), coordinate.getLongitude());
    }

    public void addMarker(GoogleMap map, Business business) {
        MarkerOptions marker = createMarkerOptions(business);
        map.addMarker(marker);
    }

    private MarkerOptions createMarkerOptions(Business business) {
        LatLng latLng = getLatLng(business);
        return new MarkerOptions()
                .position(latLng)
                .title(business.getName());
    }

    private LatLng getLatLng(Business business) {
        Coordinate coordinate = business.getCoordinate();
        return getLatLng(coordinate);
    }

}
