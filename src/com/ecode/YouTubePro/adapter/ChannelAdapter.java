package com.ecode.YouTubePro.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.ecode.YouTubePro.R;
import com.ecode.YouTubePro.model.ChannelModel;

public class ChannelAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context;
	private ArrayList<ChannelModel> listChannel;
	private AQuery mQuery ;

	public ChannelAdapter(Context ctx, ArrayList<ChannelModel> listChannel, AQuery query) {
		super();
		this.context = ctx;
		this.listChannel = listChannel;
		this.inflater= LayoutInflater.from(ctx);
		this.mQuery=query;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listChannel.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listChannel.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			holder= new ViewHolder();
			convertView=inflater.inflate(R.layout.item_channel, parent, false);
			holder.imgThumb=(ImageView)convertView.findViewById(R.id.img_channel);
			holder.tvNum=(TextView)convertView.findViewById(R.id.tv_number_video);
			holder.tvTitle=(TextView)convertView.findViewById(R.id.tv_title_channel);
			holder.lnParent=(LinearLayout)convertView.findViewById(R.id.ln_parent_channel);
			convertView.setTag(holder);
		}
		else{
			holder=(ViewHolder)convertView.getTag();
		}
		final ChannelModel model= listChannel.get(position);
		String url= model.getUrlThumb();
		mQuery.id(holder.imgThumb).image(url, true, true, 0, 0, null, 0, 1.0f);
		holder.tvNum.setText(model.getCount()+" "+context.getResources().getString(R.string.videos));
		holder.tvTitle.setText(model.getTitle());
		return convertView;
	}
	class ViewHolder{
		TextView tvTitle;
		TextView tvNum;
		ImageView imgThumb;
		LinearLayout lnParent;
	}

}
