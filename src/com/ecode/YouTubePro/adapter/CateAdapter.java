package com.ecode.YouTubePro.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecode.YouTubePro.R;

public class CateAdapter extends BaseAdapter {
	private List<String> list;
	private LayoutInflater inflater;
	private Context context;

	public CateAdapter(ArrayList<String> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHoder holder;
		if(convertView==null){
			holder=new ViewHoder();
			convertView=inflater.inflate(R.layout.item_cate, parent, false);
			holder.lnParent=(LinearLayout)convertView.findViewById(R.id.ln_parent);
			holder.divider=(View)convertView.findViewById(R.id.divider);
			holder.tv_name=(TextView)convertView.findViewById(R.id.tv_name_cate);
			convertView.setTag(holder);
		}else{
			holder=(ViewHoder)convertView.getTag();
		}
		final String name= list.get(position);
		holder.tv_name.setText(name);
		return convertView;
	}
	class ViewHoder{
		LinearLayout lnParent;
		TextView tv_name;
		View divider;
	}

}
