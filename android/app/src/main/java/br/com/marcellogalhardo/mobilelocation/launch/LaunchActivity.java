package br.com.marcellogalhardo.mobilelocation.launch;

import android.os.Bundle;
import android.support.annotation.Nullable;

import br.com.marcellogalhardo.mobilelocation.base.BaseActivity;
import br.com.marcellogalhardo.mobilelocation.nearby.NearByNavigator;

public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NearByNavigator.start(this);
        finish();
    }
}
