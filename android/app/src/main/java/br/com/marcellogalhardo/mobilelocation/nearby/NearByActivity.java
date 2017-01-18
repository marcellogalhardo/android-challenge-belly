package br.com.marcellogalhardo.mobilelocation.nearby;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.android.gms.maps.MapView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.marcellogalhardo.mobilelocation.R;
import br.com.marcellogalhardo.mobilelocation.base.BaseActivity;
import br.com.marcellogalhardo.mobilelocation.data.Business;
import br.com.marcellogalhardo.mobilelocation.data.Coordinate;
import br.com.marcellogalhardo.mobilelocation.util.AnimationUtil;
import br.com.marcellogalhardo.mobilelocation.util.GeocoderUtil;
import br.com.marcellogalhardo.mobilelocation.util.locationrequest.LocationRequestWrapper;
import br.com.marcellogalhardo.mobilelocation.util.ViewFlipperUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NearByActivity extends BaseActivity implements NearByContract.View {

    @BindView(R.id.map_view)
    MapView mapView;

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;

    @BindView(R.id.recycler_view_business)
    RecyclerView recyclerViewBusiness;

    @BindView(R.id.error_layout)
    View errorLayout;

    @BindView(R.id.loading_layout)
    View loadingLayout;

    @BindView(R.id.image_view_loader)
    ImageView imageViewLoader;

    @Inject
    NearByContract.Presenter presenter;

    @Inject
    ViewFlipperUtil viewFlipperUtil;

    @Inject
    AnimationUtil animationUtil;

    @Inject
    GeocoderUtil geocoderUtil;

    @Inject
    NearByMapHelper nearByMapHelper;

    private NearByListAdapter nearByListAdapter;
    private LocationRequestWrapper locationRequestWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by);
        ButterKnife.bind(this);
        getMainComponent().inject(this);
        mapView.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    private void init() {
        locationRequestWrapper = new LocationRequestWrapper(this, this::handleNewLocation);
        setupRecyclerView();
        presenter.bindView(this);
        showLoading();
    }

    private void setupRecyclerView() {
        nearByListAdapter = new NearByListAdapter();
        nearByListAdapter.setOnBusinessClickedListener((position, business) -> {
            mapView.getMapAsync(googleMap ->
                    nearByMapHelper.moveCamera(googleMap, business.getCoordinate()));
        });
        recyclerViewBusiness.setAdapter(nearByListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewBusiness.setLayoutManager(layoutManager);
    }

    @Override
    public void showLoading() {
        viewFlipperUtil.setDisplayedChild(viewFlipper, loadingLayout);
        animationUtil.startImageViewAnimation(imageViewLoader, R.drawable.loader_animation);
    }

    @Override
    public void showContent() {
        viewFlipperUtil.setDisplayedChild(viewFlipper, recyclerViewBusiness);
    }

    @Override
    public void showError() {
        viewFlipperUtil.setDisplayedChild(viewFlipper, errorLayout);
    }

    @Override
    public void setData(List<Business> businesses, Coordinate coordinate) {
        mapView.getMapAsync(googleMap -> {
            for (Business business : businesses) {
                double distance = geocoderUtil.calculateDistance(coordinate, business.getCoordinate());
                business.setDistance(distance);
                nearByMapHelper.addMarker(googleMap, business);
            }
            sortBusinesses(businesses);
            nearByMapHelper.moveCamera(googleMap, coordinate);
            nearByMapHelper.setupSettings(googleMap);
            nearByListAdapter.refresh(businesses);
        });
    }

    private void sortBusinesses(List<Business> businesses) {
        Collections.sort(businesses, (a, b) -> {
            if (a.getDistance() >= b.getDistance()) {
                return 1;
            } else {
                return -1;
            }
        });
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onPause() {
        super.onPause();
        locationRequestWrapper.disconnect();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        locationRequestWrapper.connect();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @OnClick(R.id.retry_button)
    void updateUi() {
        showLoading();
        locationRequestWrapper.requestLocation();
    }

    private void handleNewLocation(Location location) {
        String locality = geocoderUtil.getYourLocality(this, location);
        Coordinate coordinate = new Coordinate(location.getLatitude(), location.getLongitude());
        presenter.searchNearBy(locality, coordinate);
    }

}
