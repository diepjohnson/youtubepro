package com.ecode.YouTubePro.fragment;

import com.ecode.YouTubePro.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LocalFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view= inflater.inflate(R.layout.fragment_local, container, false);
		return view;
	}

}
