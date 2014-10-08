package com.ecode.YouTubePro.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import com.ecode.YouTubePro.R;
import com.ecode.YouTubePro.adapter.ViewPagerAdapter;
import com.ecode.YouTubePro.fragment.BaseFragment;

public class MainActivity extends Activity {
	private ViewPagerAdapter viewPagerAdapter;
	private ArrayList<BaseFragment> listFrag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


}
