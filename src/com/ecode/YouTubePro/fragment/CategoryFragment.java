package com.ecode.YouTubePro.fragment;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ecode.YouTubePro.R;
import com.ecode.YouTubePro.adapter.CateAdapter;

public class CategoryFragment extends BaseFragment {
	private CateAdapter cateAdapter;
	private ListView lvCate;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_category, container,
				false);
		String[] arr = getActivity().getResources()
				.getStringArray(R.array.cate);
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(arr));
		cateAdapter = new CateAdapter(list, getActivity());
		lvCate = (ListView) view.findViewById(R.id.lv_cate);
		lvCate.setAdapter(cateAdapter);

		return view;

	}

}
