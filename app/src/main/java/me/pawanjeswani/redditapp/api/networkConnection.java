package me.pawanjeswani.redditapp.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class networkConnection {

    public static boolean IsNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        } else {
            return false;
        }
    }

}
