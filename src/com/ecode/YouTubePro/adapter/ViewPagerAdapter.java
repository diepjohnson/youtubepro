package com.ecode.YouTubePro.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.ecode.YouTubePro.fragment.BaseFragment;
import com.viewpagerindicator.IconPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter implements
		IconPagerAdapter {
	private ArrayList<BaseFragment> mList = null;

	public ViewPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> list) {
		super(fm);
		this.mList = list;
	}

	@Override
	public int getIconResId(int index) {
		// TODO Auto-generated method stub
		return mList.get(index).getDrawable();
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return mList.get(position).getTitle();
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}

	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		return super.instantiateItem(container, position);
	}

	
}
