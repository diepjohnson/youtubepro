package com.ecode.YouTubePro.database;

import java.io.Serializable;

import com.ecode.YouTubePro.config.IConstant;


public abstract class AbstractService implements IConstant,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long localId = UNINITIALIZED_LONG;
	protected String userName = UNINITIALIZED_STRING;
	private String displayName = UNINITIALIZED_STRING;
	protected String UserID = UNINITIALIZED_STRING;
	
	private boolean isLogon = false;

	public void setUserName(String nickName) {
		this.userName = nickName;
	}


	public String getUserName() {
		return userName;
	}


	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setLogon(boolean isLogon) {
		this.isLogon = isLogon;
	}

	public boolean isLogon() {
		return isLogon;
	}

	public void setLocalId(long localId) {
		this.localId = localId;
	}

	public long getLocalId() {
		return localId;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	
}
