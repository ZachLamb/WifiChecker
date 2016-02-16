package com.example.zach.wifichecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by zach on 2/2/16.
 */
public class WifiChecker extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        int networkType = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_TYPE);
        boolean isWiFi = networkType == ConnectivityManager.TYPE_WIFI;
        boolean isMobile = networkType == ConnectivityManager.TYPE_MOBILE;
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isWiFi) {
            if (isConnected) {
                Log.i("APP_TAG", "Wi-Fi - CONNECTED");
            } else {
                Log.i("APP_TAG", "Wi-Fi - DISCONNECTED");
            }
        } else if (isMobile) {
            if (isConnected) {
                Log.i("APP_TAG", "Mobile - CONNECTED");
            } else {
                Log.i("APP_TAG", "Mobile - DISCONNECTED");
            }
        } else {
            if (isConnected) {
                Log.i("APP_TAG", activeNetwork.getTypeName() + " - CONNECTED");
            } else {
                Log.i("APP_TAG", activeNetwork.getTypeName() + " - DISCONNECTED");
            }
        }
    }
};
