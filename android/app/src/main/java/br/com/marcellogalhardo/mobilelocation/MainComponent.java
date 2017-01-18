package br.com.marcellogalhardo.mobilelocation;

import javax.inject.Singleton;

import br.com.marcellogalhardo.mobilelocation.data.storage.YelpStoreModule;
import br.com.marcellogalhardo.mobilelocation.nearby.NearByActivity;
import br.com.marcellogalhardo.mobilelocation.nearby.NearByModule;
import br.com.marcellogalhardo.mobilelocation.util.AnimationUtil;
import br.com.marcellogalhardo.mobilelocation.util.GeocoderUtil;
import br.com.marcellogalhardo.mobilelocation.util.ViewFlipperUtil;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        NearByModule.class,
        YelpStoreModule.class})
public interface MainComponent {

    void inject(MainApplication application);

    void inject(NearByActivity activity);

    AnimationUtil animationUtils();

    GeocoderUtil geocoderUtils();

    ViewFlipperUtil viewFlipperUtils();

}