package br.com.marcellogalhardo.mobilelocation.nearby;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.marcellogalhardo.mobilelocation.data.Business;
import br.com.marcellogalhardo.mobilelocation.data.BusinessList;
import br.com.marcellogalhardo.mobilelocation.data.Coordinate;
import br.com.marcellogalhardo.mobilelocation.data.Location;
import br.com.marcellogalhardo.mobilelocation.data.storage.YelpRepository;
import rx.Observable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NearByPresenterTest {

    @Mock
    private YelpRepository repository;

    @Mock
    private NearByContract.View view;

    private NearByPresenter presenter;

    @Before
    public void setup() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);
        presenter = new NearByPresenter(repository);
        presenter.bindView(view);
    }

    @After
    public void tearDown() {
        presenter.unbindView();
    }

    @Test
    public void getSuccessWhenTryToSearchNearByLocations() {
        String location = "Rio de Janeiro";
        Coordinate coordinate = new Coordinate(-23.0, -43.2);
        BusinessList locationList = createLocationList();
        when(repository.getBusinesses(location, coordinate))
                .then(invocation -> Observable.just(locationList));
        presenter.searchNearBy(location, coordinate);
        verify(view, times(1))
                .showContent();
        verify(view, times(1))
                .setData(locationList.getBusinesses(), coordinate);
    }

    @Test
    public void getErrorWhenTryToSearchNearByLocations() {
        String location = "Rio de Janeiro";
        Coordinate coordinate = new Coordinate(-23.0, -43.2);
        when(repository.getBusinesses(location, coordinate)).then(invocation -> createGenericError());
        presenter.searchNearBy(location, coordinate);
        verify(view, times(1)).showError();
    }

    @Test
    public void getErrorWhenTryToSearchNearByLocationsWithWrongParams() {
        when(repository.getBusinesses(null, null)).then(invocation -> createGenericError());
        presenter.searchNearBy(null, null);
        verify(view, times(1)).showError();
    }

    private BusinessList createLocationList() {
        List<Business> list = new ArrayList<>();
        Coordinate coordinate = new Coordinate(-23.0, -43.2);
        Location location = new Location(coordinate);
        list.add(new Business("Test", "http://wersm.com/wp-content/uploads/2013/11/kill-thumb-657x360.png", 5, false, location, null));
        return new BusinessList(list);
    }

    private Observable<BusinessList> createLocationListResponse() {
        return Observable.just(createLocationList());
    }

    private Observable createGenericError() {
        return Observable.error(new Exception());
    }


}
