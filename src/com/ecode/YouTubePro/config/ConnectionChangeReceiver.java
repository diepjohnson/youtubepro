package com.ecode.YouTubePro.config;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ecode.YouTubePro.utils.YoutubeListener;

public class ConnectionChangeReceiver extends BroadcastReceiver {
	YoutubeCore core;

	public ConnectionChangeReceiver(YoutubeCore core) {
		this.core = core;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobNetInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifiNetInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		
		if (mobNetInfo!=null && !mobNetInfo.isConnected() && 
				!wifiNetInfo.isConnected()) {
			// network fail.
			core.setConnected(false);
		} else {
			// network connected.
			core.setConnected(true);
			if (!core.isConnected()) {
				synchronized (core.getConnectionLock()) {
					core.getConnectionLock().notifyAll();
				}
			}
		}
		
		for (YoutubeListener l : core.copyAndReconstructListeners(null)) {
			l.networkConnectionChange(core.isConnected());
		}
		
	}
}
