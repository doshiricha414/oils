package com.addit.drjainsoils.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class Network {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /*public static boolean isAppSupported() {
        boolean isAppSupported = (AppContext.IMEI != null && !AppContext.IMEI.equals(""));
        char[] imei = AppContext.IMEI.toCharArray();
        boolean flag = false;
        if (isAppSupported)
            for (int i = 0; i < AppContext.IMEI.length(); i++)
                if (imei[i] != '0' && (flag = true))
                    break;
        return isAppSupported && flag;
    }*/
}