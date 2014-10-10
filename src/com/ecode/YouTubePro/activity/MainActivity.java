package com.ecode.YouTubePro.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.ecode.YouTubePro.R;
import com.ecode.YouTubePro.adapter.ViewPagerAdapter;
import com.ecode.YouTubePro.fragment.BaseFragment;
import com.ecode.YouTubePro.fragment.CategoryFragment;
import com.ecode.YouTubePro.fragment.ChanneFragment;
import com.ecode.YouTubePro.fragment.LocalFragment;
import com.viewpagerindicator.TitlePageIndicator;

public class MainActivity extends FragmentActivity implements OnClickListener,
		OnPageChangeListener {
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
	private ImageView imgSearch;
	private ImageView imgSetting;
	private ImageView imgAccount;
	private ImageView imgEdit;
	private ImageView imgBookmark;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager = getSupportFragmentManager();
		initUIComponent();
	}

	private void initUIComponent() {
		imgAccount = (ImageView) findViewById(R.id.img_account);
		imgBookmark = (ImageView) findViewById(R.id.img_add);
		imgEdit = (ImageView) findViewById(R.id.img_edit);
		imgSearch = (ImageView) findViewById(R.id.img_search_home);
		imgSetting = (ImageView) findViewById(R.id.img_setting);
		imgAccount.setOnClickListener(this);
		imgBookmark.setOnClickListener(this);
		imgEdit.setOnClickListener(this);
		imgSearch.setOnClickListener(this);
		imgSetting.setOnClickListener(this);
		mPager = (ViewPager) findViewById(R.id.pager);
		mTabPageIndicator = (TitlePageIndicator) findViewById(R.id.indicator);
		listFragments = new ArrayList<BaseFragment>();
		localFragment = new LocalFragment();
		bookmarkFragment = new LocalFragment();
		bookmarkFragment.setTitle(this
				.getString(R.string.fragment_title_bookmark));
		localFragment.setTitle(this.getString(R.string.fragment_title_local));
		channeFragment = new ChanneFragment();
		channeFragment
				.setTitle(this.getString(R.string.fragment_title_channel));
		youtubeAcountFragment = new ChanneFragment();
		youtubeAcountFragment.setTitle(this
				.getString(R.string.fragment_title_youtube));
		categoryFragment = new CategoryFragment();
		categoryFragment.setTitle(this
				.getString(R.string.fragment_title_category));
		listFragments.add(localFragment);
		listFragments.add(channeFragment);
		listFragments.add(youtubeAcountFragment);
		listFragments.add(categoryFragment);
		listFragments.add(bookmarkFragment);
		viewPagerAdapter = new ViewPagerAdapter(manager, listFragments);
		mPager.setAdapter(viewPagerAdapter);
		mTabPageIndicator.setViewPager(mPager);
		mTabPageIndicator.setCurrentItem(2);
		imgBookmark.setEnabled(false);
		imgAccount.setEnabled(true);
		imgEdit.setEnabled(false);
		mTabPageIndicator.setOnPageChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.img_account:

			break;
		case R.id.img_add:

			break;
		case R.id.img_edit:

			break;
		case R.id.img_search_home:

			break;
		case R.id.img_setting:

			break;

		default:
			break;
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			imgAccount.setEnabled(false);
			imgBookmark.setEnabled(true);
			imgEdit.setEnabled(false);
			break;
		case 1:
			imgAccount.setEnabled(true);
			imgBookmark.setEnabled(false);
			imgEdit.setEnabled(false);
			break;
		case 2:
			imgBookmark.setEnabled(false);
			imgAccount.setEnabled(true);
			imgEdit.setEnabled(false);
			break;
		case 3:
			imgBookmark.setEnabled(false);
			imgAccount.setEnabled(false);
			imgEdit.setEnabled(false);
			break;
		case 4:
			imgBookmark.setEnabled(true);
			imgAccount.setEnabled(false);
			imgEdit.setEnabled(false);
			break;

		default:
			break;
		}
	}

}
