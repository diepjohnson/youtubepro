package com.ecode.YouTubePro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecode.YouTubePro.R;
import com.ecode.YouTubePro.database.BuiltinService;
import com.ecode.YouTubePro.database.BuiltinServiceManager;

public class ChanneFragment extends BaseFragment {
	private BuiltinService service;
	private BuiltinServiceManager builtinServiceManager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		builtinServiceManager = BuiltinServiceManager.getInstance(getActivity());
		service = builtinServiceManager.getBuitinService();
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view =inflater.inflate(R.layout.fragment_channel, container, false);
		return view;
	}

}
