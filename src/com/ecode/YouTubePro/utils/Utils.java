package com.ecode.YouTubePro.utils;

import java.math.BigDecimal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

public class Utils {
	public static String validateValue(String value) {
		if (value != null && !value.equals("") && !value.equals("null")) {
			return value;
		}
		return "";

	}

	public static int validateIntValue(int intValue) {
		if (intValue != 0) {
			return intValue;
		}
		return 0;

	}

	public static float validateFloatValue(float floatValue) {
		if (floatValue != 0) {
			return floatValue;
		}
		return 0;

	}

	public static int[] splitToComponentTimes(BigDecimal biggy) {
		long longVal = biggy.longValue();
		int hours = (int) longVal / 3600;
		int remainder = (int) longVal - hours * 3600;
		int mins = remainder / 60;
		remainder = remainder - mins * 60;
		int secs = remainder;

		int[] ints = { hours, mins, secs };
		return ints;
	}

	public static String encodeUrl(String request) {

		return Uri.encode(request);
	}

}
