package com.duke.nurseryschool.helper;

public final class Constant {
	public static final String EMPTY_STRING = "";
	public static final String SPACE = " ";

	public static final class ACTION_RESULT {
		public static final String SUCCESS_REDIRECT = "successRedirect";
	}

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

		public static final String SUCCESS_MODEL_CREATE = "success.model.create";
	}

	public static final class DATABASE_QUERY {
		public static final String ALL_CLASSES = "from Classes";
		public static final String ALL_COURSES = "from Course";
		public static final String ALL_EXTRA_FEE_TYPES = "from ExtraFeeType";
		public static final String ALL_FEE_DETAILS = "from FeeDetails";
		public static final String ALL_FEE_POLICIES = "from FeePolicy";
		public static final String ALL_MONTHS = "from Month";
		public static final String ALL_PARENTS = "from Parent";
		public static final String ALL_PAYMENTS = "from Payment";
		public static final String ALL_STUDENTS = "from Student";
		public static final String ALL_SUBJECTS = "from Subject";
	}

	public static final class BUSINESS_LOGIC {
	}
}
