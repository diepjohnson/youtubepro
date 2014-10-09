package com.ecode.YouTubePro.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.ecode.YouTubePro.R;
import com.ecode.YouTubePro.adapter.ViewPagerAdapter;
import com.ecode.YouTubePro.fragment.BaseFragment;
import com.ecode.YouTubePro.fragment.CategoryFragment;
import com.ecode.YouTubePro.fragment.ChanneFragment;
import com.ecode.YouTubePro.fragment.LocalFragment;
import com.viewpagerindicator.TitlePageIndicator;

public class MainActivity extends FragmentActivity {
	private FragmentManager manager;
	private ViewPagerAdapter viewPagerAdapter;
	private LocalFragment localFragment;
	private LocalFragment bookmarkFragment;
	private ChanneFragment channeFragment;
	private ChanneFragment youtubeAcountFragment;
	private CategoryFragment categoryFragment;
	private ArrayList<BaseFragment> listFragments;
	public TitlePageIndicator mTabPageIndicator;
	private ViewPager mPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager = getSupportFragmentManager();
		initUIComponent();
	}

	private void initUIComponent() {
		mPager = (ViewPager) findViewById(R.id.pager);
		mTabPageIndicator = (TitlePageIndicator) findViewById(R.id.indicator);
		listFragments = new ArrayList<BaseFragment>();
		localFragment = new LocalFragment();
		bookmarkFragment= new LocalFragment();
		bookmarkFragment.setTitle(this.getString(R.string.fragment_title_bookmark));
		localFragment.setTitle(this.getString(R.string.fragment_title_local));
		channeFragment = new ChanneFragment();
		channeFragment.setTitle(this.getString(R.string.fragment_title_channel));
		youtubeAcountFragment= new ChanneFragment();
		youtubeAcountFragment.setTitle(this.getString(R.string.fragment_title_youtube));
		categoryFragment = new CategoryFragment();
		categoryFragment.setTitle(this.getString(R.string.fragment_title_category));
		listFragments.add(localFragment);
		listFragments.add(channeFragment);
		listFragments.add(youtubeAcountFragment);
		listFragments.add(categoryFragment);
		listFragments.add(bookmarkFragment);
		viewPagerAdapter=new ViewPagerAdapter(manager, listFragments);
		mPager.setAdapter(viewPagerAdapter);
		mTabPageIndicator.setViewPager(mPager);
		mTabPageIndicator.setCurrentItem(2);
	}

}
