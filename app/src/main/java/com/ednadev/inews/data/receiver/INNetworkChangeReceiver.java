package com.ednadev.inews.data.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import com.ednadev.inews.R;
import com.ednadev.inews.components.dialogs.INAlertDialogView;


public class INNetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);

        if (wifiStateExtra == WifiManager.WIFI_STATE_ENABLED)
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
        else if (wifiStateExtra == WifiManager.WIFI_STATE_DISABLED) {
            INAlertDialogView alertDialog = new INAlertDialogView(context);
            alertDialog.setMessage(context.getString(R.string.error_internet_connection));
            alertDialog.initPositiveButton(context.getString(R.string.close));
            alertDialog.setTitleIcon(R.drawable.ic_error, R.color.colorError, true);
            alertDialog.show();
        }
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (connectivityManager != null) {
//                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
//                if (capabilities != null) {
//                    if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED))
//                        Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
//                    else
//                        Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
//                }
//            }
//        } else {
//            if (connectivityManager != null) {
//                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
//                if (activeNetwork != null) {
//                    if (activeNetwork.isAvailable() && activeNetwork.isConnected())
//                        Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
//                    else
//                        Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
    }
}
