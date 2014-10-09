package com.ecode.YouTubePro.database;

public class BuiltinService extends AbstractService {

	private String userId = UNINITIALIZED_STRING;
	private String UserEmail = UNINITIALIZED_STRING;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}


	

}
