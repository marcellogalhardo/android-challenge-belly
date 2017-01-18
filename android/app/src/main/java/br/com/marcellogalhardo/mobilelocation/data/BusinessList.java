package br.com.marcellogalhardo.mobilelocation.data;

import java.util.List;

public class BusinessList {

    private List<Business> businesses;

    public BusinessList(List<Business> businesses) {
        this.businesses = businesses;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

}
