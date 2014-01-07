package com.duke.nurseryschool.helper;

public final class Constant {
	public static final String EMPTY_STRING = "";

	public static final String RESPONSE_SUCCESS = "success";
	public static final String RESPONSE_ERROR = "error";

	public static final class TAG {
		public static final String LOGIN_USERNAME = "username";
		public static final String LOGIN_PASSWORD = "password";
	}

	// When modifying key in global.properties => must also modify this class
	// appropriately
	public static final class I18N {
		public static final String ERROR_LOGIN_USERNAME = "error.login.username";
		public static final String ERROR_LOGIN_PASSWORD = "error.login.password";
	}
}
