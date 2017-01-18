package br.com.marcellogalhardo.mobilelocation.nearby;

import android.content.Context;
import android.content.Intent;

public class NearByNavigator {

    public static void start(Context context) {
        Intent intent = new Intent(context, NearByActivity.class);
        context.startActivity(intent);
    }

}
