package br.com.marcellogalhardo.mobilelocation.nearby;

import javax.inject.Inject;

import br.com.marcellogalhardo.mobilelocation.data.Coordinate;
import br.com.marcellogalhardo.mobilelocation.data.storage.YelpRepository;

public class NearByPresenter implements NearByContract.Presenter {

    private NearByContract.View view;
    private YelpRepository yelpRepository;

    @Inject
    public NearByPresenter(YelpRepository yelpRepository) {
        this.yelpRepository = yelpRepository;
    }

    @Override
    public void bindView(NearByContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void searchNearBy(String location, Coordinate coordinate) {
        yelpRepository.getBusinesses(location, coordinate)
                .subscribe(businesses -> {
                    view.showContent();
                    view.setData(businesses.getBusinesses(), coordinate);
                }, throwable -> {
                    view.showError();
                });
    }
}
