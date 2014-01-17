package com.duke.nurseryschool.helper;

public final class Constant {
	public static final String EMPTY_STRING = "";

	public static final class TAG {
		public static final String LOGIN_USERNAME = "username";
		public static final String LOGIN_PASSWORD = "password";
	}

	// TODO
	// When modifying key in global.properties => must also modify this class
	// appropriately
	public static final class I18N {
		public static final String ERROR_LOGIN_USERNAME = "error.login.username";
		public static final String ERROR_LOGIN_PASSWORD = "error.login.password";
	}

	public static final class DATABASE_QUERY {
		public static final String ALL_MONTHS = "from Month";
		public static final String ALL_STUDENTS = "from Student";
		public static final String ALL_SUBJECTS = "from Subject";
	}

	public static final class BUSINESS_LOGIC {
	}
}
