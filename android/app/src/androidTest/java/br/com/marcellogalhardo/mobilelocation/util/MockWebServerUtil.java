package br.com.marcellogalhardo.mobilelocation.util;

import android.app.Activity;
import android.support.annotation.NonNull;

import br.com.marcellogalhardo.mobilelocation.R;
import okhttp3.mockwebserver.MockResponse;

public class MockWebServerUtil {

    @NonNull
    public static MockResponse getLocationsFake(Activity activity) {
        RawUtil rawUtil = new RawUtil();
        String body = rawUtil.readTextFile(activity.getResources(), R.raw.mock_get_locations);
        return new MockResponse().setResponseCode(200)
                .addHeader("Content-Type", "application/json")
                .setBody(body);
    }

    @NonNull
    public static MockResponse getGenericError() {
        return new MockResponse().setResponseCode(500);
    }

}