package com.ecode.YouTubePro.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;

import com.ecode.YouTubePro.config.YoutubeCore;

public class JSONProtocol implements IProtocol{
	String TAG = "JSONProtocol";
	private DefaultHttpClient httpclient;
	private Context context;

	public JSONProtocol(Context context, YoutubeCore core) {
		this.context = context;
	}
	private synchronized String getDataServerPost(String url)
			throws ClientProtocolException, IOException {
		String responseStr = "";
		StringBuilder builder = new StringBuilder();
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpRes = httpClient.execute(httpGet);
			int code =httpRes.getStatusLine().getStatusCode();
			if (code == 200) {
				HttpEntity entity = httpRes.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content), 8192);
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				responseStr = builder.toString();
			}

			Log.d("DATASTR", "Res : " + responseStr);

		} catch (ConnectTimeoutException e) {
			Log.e("JSONProtocol 1070", e.getMessage());
			throw new ClientProtocolException();
		} catch (SocketTimeoutException e) {
			Log.e("JSONProtocol 1073", e.getMessage());
			throw new ClientProtocolException();
		} catch (SocketException e) {
			Log.e("JSONProtocol 1076", e.getMessage());
			throw new ClientProtocolException();
		} catch (UnsupportedEncodingException e) {
			Log.e("JSONProtocol 1079", e.getMessage());
			throw new ClientProtocolException();
		} catch (ClientProtocolException e) {
			Log.e("JSONProtocol 1082", e.getMessage());
			throw e;
		}
		return responseStr;

	}



}
