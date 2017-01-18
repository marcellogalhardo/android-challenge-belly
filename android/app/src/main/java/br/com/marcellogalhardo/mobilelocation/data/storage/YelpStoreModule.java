package br.com.marcellogalhardo.mobilelocation.data.storage;

import br.com.marcellogalhardo.mobilelocation.data.storage.client.YelpProvider;
import br.com.marcellogalhardo.mobilelocation.data.storage.client.YelpService;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import retrofit2.Retrofit;

@Module
public class YelpStoreModule {

    @Reusable
    @Provides
    YelpRepository providesYelpRepository(YelpService yelpService, YelpProvider yelpProvider) {
        return new YelpRepository(yelpService, yelpProvider);
    }

    @Reusable
    @Provides
    YelpService providesYelpService(Retrofit retrofit) {
        return retrofit.create(YelpService.class);
    }

    @Reusable
    @Provides
    YelpProvider providesYelpProvider() {
        return new YelpProvider();
    }

}
