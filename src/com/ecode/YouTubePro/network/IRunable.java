package com.ecode.YouTubePro.network;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

public interface IRunable {
	public void run() throws ClientProtocolException, JSONException,
	IOException;

public void timeoutCallback();
}
