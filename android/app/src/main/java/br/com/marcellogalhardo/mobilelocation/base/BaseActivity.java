package br.com.marcellogalhardo.mobilelocation.base;

import android.support.v7.app.AppCompatActivity;

import br.com.marcellogalhardo.mobilelocation.MainApplication;
import br.com.marcellogalhardo.mobilelocation.MainComponent;

public class BaseActivity extends AppCompatActivity {

    protected MainComponent getMainComponent() {
        return getMainApplication().getComponent();
    }

    protected MainApplication getMainApplication() {
        return (MainApplication) getApplication();
    }

}
