package com.ecode.YouTubePro.config;

public interface IConstant {

	// sleep wait time
	public final int WAIT_TIME = 10 * 1000;

	public final String UNINTIALIZED_MIMETYPE = "text/plain";
	public final int UNINITIALIZED_INT = -1;
	public final int UNINITIALIZED_INT_ZERO = 0;
	public final long UNINITIALIZED_LONG = -1;
	public final long UNINITIALIZED_LONG_ZERO = 0;
	public final double UNINITIALIZED_DOUBLE = Double.NaN;
	public final String UNINITIALIZED_STRING = "";

	public static final int TYPE_BUITIN = 0;
	public static final int TYPE_GATEWAY = 1;

	//
	public static final int UNREGISTERED = 0;
	public static final int REGISTERED = 1;
	public static final int REGISTERING = 2;


//	// We distinguish gateway services by their name
//	public static final String GS_MSN = "msn";
//	public static final String GS_YAHOO = "yahoo";
//	public static final String GS_GOOGLE_TALK = "GoogleTalk";
	
//	Key of remoteID
	public static final String KEY_MSN = "1";
	public static final String KEY_YAHOO = "2";
	public static final String KEY_GOOGLE_TALK = "3";

	// logon action
	public static final int LOGON = 1;
	public static final int LOGOFF = 0;

	// register
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;

	
	
	
	// public static final String SHOW_MUSIC_TRACK = "SHOW_MUSIC_TRACK";

	// subscription please search IM Use cases and protocol.doc Appendix B   Subscription
	public static final int UNSUBSCRIBED = 0; // Unsubscribed
	public static final int SUBSCRIBED = 1; // Subscribed
	public static final int SUBSCRIBE = 2; // Subscribe
	public static final int UNSUBSCRIBE = 3; // Unsubscribe
	public static final int DELETE_AND_BLOCK = 4;
	public static final int BLOCK = 5;
	public static final int BLOCKED = 6;//Ignore and block
	public static final int UNBLOCK = 7;

	// returned codes for key "result"
	public static final int GW_REGISTER_SUCCESS = 1;
	public static final int GW_REGISTER_AUTHENTICATE_FAIL = 2;
	public static final int GW_REGISTER_OTHER_REASON_FAIL = 3;

	public static final int GW_UNREGISTER_SUCCESS = 1;
	public static final int GW_UNREGISTER_FAIL = 0;

	public static final int GW_LOGIN_SUCCESS = 1;
	public static final int GW_LOGIN_AUTHENTICATE_FAIL = 2;
	public static final int GW_LOGIN_OTHER_REASON_FAIL = 3;
	public static final int GW_LOGIN_ALREADY = 4;

	public static final int GW_ACTIVE_LOGOFF_SUCCESS = 1;
	public static final int GW_ACTIVE_LOGOFF_FAIL = 0;
	public static final int GW_PASSIVE_LOGOFF_OTHER_LOCATION = 2;
	public static final int GW_PASSIVE_LOGOFF_SERVER_SHUTDOWN = 3;
	public static final int GW_PASSIVE_LOGOFF_OTHER_REASON = 4;
	public static final int GW_PASSIVE_LOGOFF_ALREADY = 5;

	public static final int GW_PRESENCE_CHANGE_UPDATE_SUCCESS = 1;
	public static final int GW_PRESENCE_CHANGE_UPDATE_FAIL = 0;

	public static final int GW_STATUS_CHANGE_UPDATE_SUCCESS = 1;
	public static final int GW_STATUS_CHANGE_UPDATE_FAIL = 0;

	public static final int GW_DISPLAY_NAME_UPDATE_SUCCESS = 1;
	public static final int GW_DISPLAY_NAME_UPDATE_FAIL = 0;

	public static final int GW_DISPLAY_PICTURE_UPDATE_SUCCESS = 1;
	public static final int GW_DISPLAY_PICTURE_UPDATE_FAIL = 0;

	// message type in user queue
	public static final int ONLINE_MESSAGE = 1;
	public static final int OFFLINE_MESSAGE = 0;

	// GatewayService status of logon
	public static final int GW_LOGON_STATUS_NO = -1;
	public static final int GW_LOGON_STATUS_WAIT = 0;
	public static final int GW_LOGON_STATUS_YES = 1;

	// @<IM-Service-Domain>
	public static final String BUILT_IN_DOMAIN = "@<IM-Service-Domain>";

	public static final String HEARTBEAT_UDP = "UDP";
	public static final String HEARTBEAT_HTTP = "HTTP";

	public static final String ID_TYPE_USER = "User_Id";
	public static final String ID_TYPE_ROSTER = "Roster_Id";

	// Logon status
	public static final int LOGON_NO = 0;
	public static final int LOGON_PROGRESS = 1;
	public static final int LOGON_YES = 2;

	public static final int USER_LEVEL_NORMAL = 0;
	public static final int USER_LEVEL_PREMIUM = 1;
	public static final int USER_LEVEL_CORPORATION = 2;

	// Return code for add roster delete roster
	// public static final int RETURN_ADD_ROSTER_OK = 200;
	// public static final int RETURN_ADD_ROSTER_ALREADY_EXIST = 420;
	// public static final int RETURN_ADD_ROSTER_NOT_EXIST = 424;
	// public static final int RETURN_DELETE_ROSTER_OK = 200;
	// public static final int RETURN_DELETE_ROSTER_NOT_EXIST = 424;

	public static final String OBJECT_TYPE_POST = "Post";
	public static final String OBJECT_TYPE_COMMENT = "Comment";
	public static final String OBJECT_TYPE_PHOTO = "Photo";
	public static final String OBJECT_TYPE_VIDEO = "Video";
	public static final String OBJECT_TYPE_NOTE = "Note";

	public static final String SOURCE_TYPE_PHOTO = "Photo";
	public static final String SOURCE_TYPE_POST = "Post";
	public static final String SOURCE_TYPE_VIDEO = "video";
	public static final String SOURCE_TYPE_NOTE = "Note";

	public static final int AD_RANDOM = 0;
	public static final int AD_BUILTIN = 1;
	public static final int AD_ADMOB = 2;

	// New Fields in the Logon
	public static final String LOGON_OS = "Android";
	public static final String LOGON_OS_CUSTOMIZED_VERSION = "2.3";

	

	public static final long ONEDAY = 86400000;

	public static final String SCREEN_WIDTH_KEY = "screen_with";
	public static final String SCREEN_HEIGHT_KEY = "screen_height";


}
