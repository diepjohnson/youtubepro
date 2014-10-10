package com.ecode.YouTubePro.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ecode.YouTubePro.R;

public class SearchActivity extends Activity {
	public static void actionLaunch(Context context){
		Intent intent = new Intent(context,SearchActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitvity_search);
	}

}
