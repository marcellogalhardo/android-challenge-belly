package br.com.marcellogalhardo.mobilelocation.nearby;

import java.util.List;

import br.com.marcellogalhardo.mobilelocation.data.Business;
import br.com.marcellogalhardo.mobilelocation.data.Coordinate;

public interface NearByContract {

    interface View {

        void showLoading();

        void showContent();

        void showError();

        void setData(List<Business> businesses, Coordinate coordinate);

    }

    interface Presenter {

        void bindView(NearByContract.View view);

        void unbindView();

        void searchNearBy(String location, Coordinate coordinate);

    }

}
