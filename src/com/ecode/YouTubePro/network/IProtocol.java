package com.ecode.YouTubePro.network;


public interface IProtocol {
	public static final String URL_GET = "https://gdata.youtube.com/feeds/api/videos?q=";

	public static final int TIMEOUT_SOCKET = 30 * 1000;
	public final int STATUS_CODE_OK = 1;
	public final int STATUS_CODE_GENERIC_FAIL = 0;
	public final int STATUS_CODE_GENERIC_FAIL_CONECTTION = 5;
}
