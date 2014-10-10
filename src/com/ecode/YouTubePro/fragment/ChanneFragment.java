package com.ecode.YouTubePro.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.ecode.YouTubePro.R;
import com.ecode.YouTubePro.adapter.ChannelAdapter;
import com.ecode.YouTubePro.database.BuiltinService;
import com.ecode.YouTubePro.database.BuiltinServiceManager;
import com.ecode.YouTubePro.model.ChannelModel;
import com.ecode.YouTubePro.widgets.ChannelDialog;

public class ChanneFragment extends BaseFragment implements OnClickListener {
	private BuiltinService service;
	private BuiltinServiceManager builtinServiceManager;
	private Button btnSpecify;
	private Button btnLogindev;
	private Button btnLoginweb;
	private TextView tvNewVideo;
	private ListView lvChanenel;
	private ProgressBar pgBar;
	private RelativeLayout rlLogin, rlLstChannel; 
	private WebView wvLogin;
	private JSONObject obj=null;
	private ArrayList<ChannelModel> listChannel= new ArrayList<ChannelModel>();
	protected AQuery aq;
	private ChannelAdapter channelAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		builtinServiceManager = BuiltinServiceManager
				.getInstance(getActivity());
		service = builtinServiceManager.getBuitinService();
		aq = new AQuery(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_channel, container,
				false);
		btnSpecify = (Button) view
				.findViewById(R.id.btn_specified_name_channel);
		btnLogindev = (Button) view.findViewById(R.id.login_dev);
		btnLoginweb = (Button) view.findViewById(R.id.login_web);
		tvNewVideo = (TextView) view.findViewById(R.id.tv_new_videos_sub);
		lvChanenel = (ListView) view.findViewById(R.id.list_channel_subcribe);
		pgBar = (ProgressBar) view.findViewById(R.id.progress);
		
		rlLogin=(RelativeLayout)view.findViewById(R.id.rllogin);
		rlLstChannel=(RelativeLayout)view.findViewById(R.id.rllst_channel);
		
		wvLogin=(WebView)view.findViewById(R.id.wvLogin);
		rlLogin.setVisibility(View.VISIBLE);
		rlLstChannel.setVisibility(View.GONE);
		wvLogin.setVisibility(View.GONE);

		btnLogindev.setOnClickListener(this);
		btnLoginweb.setOnClickListener(this);
		btnSpecify.setOnClickListener(this);
		tvNewVideo.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_specified_name_channel:
			ChannelDialog dialog = new ChannelDialog(getActivity());
			dialog.show();
			break;
		case R.id.login_dev:
			auth_youtube();
			break;
		case R.id.login_web:
			WebSettings ws=wvLogin.getSettings();
			ws.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		    ws.getPluginState();
		    ws.setPluginState(PluginState.ON);
		    ws.setJavaScriptEnabled(true);
//		    ws.setUserAgent(0);
		    ws.setJavaScriptCanOpenWindowsAutomatically(true);

		    wvLogin.setWebChromeClient(new WebChromeClient() {
		    });
		    wvLogin.loadDataWithBaseURL(getActivity().getString(R.string.wv_url),"", "text/html", "UTF-8", null);

			wvLogin.loadUrl(getActivity().getResources().getString(R.string.wv_url));
		

			wvLogin.setVisibility(View.VISIBLE);
			break;
		case R.id.tv_new_videos_sub:
			break;

		default:
			break;
		}
	}


	public void auth_youtube() {

		String url = "https://gdata.youtube.com/feeds/api/users/default/subscriptions?v=2&alt=json";

		AjaxCallback<JSONObject> cb = new AjaxCallback<JSONObject>();
		cb.url(url).type(JSONObject.class).weakHandler(this, "youtubeCb")
				.fileCache(true).expire(15 * 60 * 1000);

		cb.auth(getActivity(), AQuery.AUTH_YOUTUBE, AQuery.ACTIVE_ACCOUNT);
		String email=AQuery.ACTIVE_ACCOUNT;
		aq.progress(R.id.progress).ajax(cb);

	}

	public void youtubeCb(String url, JSONObject jo, AjaxStatus status) {

		if (jo != null) {
			JSONArray entries = jo.optJSONObject("feed").optJSONArray("entry");
			obj = jo.optJSONObject("feed");
			JSONArray jArray= obj.optJSONArray("entry");
			for(int i=0;i<jArray.length();i++){
				JSONObject entry=jArray.optJSONObject(i);
				String value="";
				int intValue;
				ChannelModel model= new ChannelModel();
				value=entry.optJSONObject("yt$username").optString("display");
				model.setTitle(value);
				value=entry.optJSONObject("media$thumbnail").optString("url");
				model.setUrlThumb(value);
				intValue=entry.optJSONObject("yt$countHint").optInt("$t");
				model.setCount(intValue);
				value=entry.optJSONObject("yt$channelId").optString("$t");
				model.setId(value);
				listChannel.add(model);
			}
			channelAdapter= new ChannelAdapter(getActivity(), listChannel, aq);
			lvChanenel.setAdapter(channelAdapter);
			rlLogin.setVisibility(View.GONE);
			rlLstChannel.setVisibility(View.VISIBLE);
			wvLogin.setVisibility(View.GONE);
			
		}

	}

}
