package br.com.marcellogalhardo.mobilelocation.nearby;

import javax.inject.Singleton;

import br.com.marcellogalhardo.mobilelocation.data.storage.YelpRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class NearByModule {

    @Provides
    @Singleton
    NearByContract.Presenter providesNearByPresenter(YelpRepository yelpRepository) {
        return new NearByPresenter(yelpRepository);
    }

    @Provides
    @Singleton
    NearByMapHelper providesNearByMapHelper() {
        return new NearByMapHelper();
    }

}
