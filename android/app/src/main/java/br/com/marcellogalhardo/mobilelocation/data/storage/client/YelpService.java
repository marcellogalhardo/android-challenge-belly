package br.com.marcellogalhardo.mobilelocation.data.storage.client;

import br.com.marcellogalhardo.mobilelocation.data.BusinessList;
import br.com.marcellogalhardo.mobilelocation.data.Coordinate;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface YelpService {
    @GET("v2/search")
    Observable<BusinessList> search(@Query("location") String location,
                                    @Query("cll") Coordinate coordinate);
}
