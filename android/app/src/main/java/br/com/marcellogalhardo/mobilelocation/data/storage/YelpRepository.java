package br.com.marcellogalhardo.mobilelocation.data.storage;

import java.io.IOException;

import javax.inject.Inject;

import br.com.marcellogalhardo.mobilelocation.data.BusinessList;
import br.com.marcellogalhardo.mobilelocation.data.Coordinate;
import br.com.marcellogalhardo.mobilelocation.data.storage.client.YelpProvider;
import br.com.marcellogalhardo.mobilelocation.data.storage.client.YelpService;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class YelpRepository {

    private YelpService yelpService;
    private YelpProvider yelpProvider;

    @Inject
    public YelpRepository(YelpService yelpService, YelpProvider yelpProvider) {
        this.yelpService = yelpService;
        this.yelpProvider = yelpProvider;
    }

    public Observable<BusinessList> getBusinesses(String location, Coordinate coordinate) {
        return yelpService.search(location, coordinate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap(businesses -> {
                    yelpProvider.delete();
                    return yelpProvider.add(businesses);
                })
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof IOException) {
                        return yelpProvider.getAll();
                    }
                    return Observable.error(throwable);
                });
    }

}
