package br.com.marcellogalhardo.mobilelocation.data.storage.client;

import com.orhanobut.hawk.Hawk;

import br.com.marcellogalhardo.mobilelocation.data.BusinessList;
import rx.Observable;

public class YelpProvider {

    private static final String TAG = YelpProvider.class.getSimpleName();

    public Observable<BusinessList> add(BusinessList businesses) {
        return Observable.create(subscriber -> {
            try {
                Hawk.put(TAG, businesses);
                subscriber.onNext(businesses);
                subscriber.onCompleted();
            } catch (Exception exception) {
                subscriber.onError(exception);
            }
        });

    }

    public Observable<BusinessList> getAll() {
        return Observable.just(Hawk.get(TAG));
    }

    public Observable<Void> delete() {
        return Observable.create(subscriber -> {
            try {
                Hawk.delete(TAG);
                subscriber.onNext(null);
                subscriber.onCompleted();
            } catch (Exception exception) {
                subscriber.onError(exception);
            }
        });
    }


}
