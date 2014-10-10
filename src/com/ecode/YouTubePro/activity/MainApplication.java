package com.ecode.YouTubePro.activity;

import android.app.Application;
import android.os.StrictMode;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.BitmapAjaxCallback;
import com.androidquery.util.AQUtility;
import com.ecode.YouTubePro.utils.TestUtility;

public class MainApplication extends Application {
	public static final boolean DEBUG = false;

	public static boolean autoStart = true;
	@Override
	public void onCreate() {
		if (AQuery.SDK_INT >= 9 && TestUtility.isTestDevice(this)) {
			AQUtility.debug("enable strict mode!");

			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectAll().penaltyLog().build());

			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectAll().penaltyLog()
					// .penaltyDeath()
					.build());
		}
		AjaxCallback.setNetworkLimit(8);
		BitmapAjaxCallback.setIconCacheLimit(40);
		BitmapAjaxCallback.setCacheLimit(40);
		BitmapAjaxCallback.setPixelLimit(500 * 500);
		BitmapAjaxCallback.setMaxPixelLimit(8000000);
		super.onCreate();
	}
	public static boolean isAutoStart(){
		return autoStart;
	}

	public static void setAutoStart(boolean aAutoStart){
		autoStart = aAutoStart;
	}
	@Override
	public void onLowMemory() {
		BitmapAjaxCallback.clearCache();
	}

}
