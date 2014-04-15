package com.duke.nurseryschool.helper;

public final class Constant {
	public static final String EMPTY_STRING = "";
	public static final String SPACE = " ";
	public static final String ZERO = "0";

	public static final String SESSION_USER = "USER";
	// Timeout = 14 days
	public static final int COOKIE_TIME_OUT = 14 * 24 * 60 * 60;

	public static final String EXCEL_SUFFIX = ".xls";

	public static final String USER_GUIDE_FILE_PATH = "docs/[Nursery School] User Guide.pdf";
	public static final String USER_GUIDE_FILE_NAME = "[Nursery School] User Guide.pdf";

	public static final String ROOT_USERNAME = "admin";
	public static final String ROOT_PASSWORD = "admin";

	public static final class PUNCTUATION_MARK {
		public static final String BRACKET_SQUARE_OPEN = "[";
		public static final String BRACKET_SQUARE_CLOSE = "]";
		public static final String BRACKET_CURLY_OPEN = "{";
		public static final String BRACKET_CURLY_CLOSE = "}";
		public static final String PARENTHESIS_OPEN = "(";
		public static final String PARENTHESIS_CLOSE = ")";
		public static final String HYPHEN = "-";
	}

	public static final class ACTION_RESULT {
		public static final String SUCCESS_REDIRECT = "successRedirect";
		public static final String CLONE = "clone";
		public static final String CLONE_ALL = "cloneAll";
		public static final String LOGOUT_SUCCESS = "LogoutSuccess";
		public static final String MESSAGE = "message";
		public static final String BATCH_EDIT = "batch";
	}

	public static final class UI {
		public static final String CURRENT = "current";
		public static final String OPENED = "opened";
		public static final String ACTIVE_IN = "active in";
		public static final String ACTIVE = "active";
		public static final String APP_HIDDEN = "app-hidden";
	}

	public static final class TAG {
		public static final String LOGIN_USERNAME = "username";
		public static final String LOGIN_PASSWORD = "password";
		public static final String LOGIN_REMEMBER_ME = "rememberMe";
		public static final String COOKIE_USERNAME = "userName";

		public static final String ACTION_PREFIX_EDIT = "edit";
		public static final String ACTION_PREFIX_AUTOSET = "autoSet";
	}

	public static final class DATABASE_QUERY {
		public static final String ALL_CLASSES = "from Classes";
		public static final String ALL_COURSES = "from Course";
		public static final String ALL_FEE_GROUPS = "from FeeGroup";
		public static final String ALL_FEE_MAPS = "from FeeMap";
		public static final String ALL_FEE_POLICIES = "from FeePolicy";
		public static final String ALL_MONTHS = "from Month";
		public static final String ALL_PARENTS = "from Parent";
		public static final String ALL_PAYMENTS = "from Payment";
		public static final String ALL_STUDENTS = "from Student";
		public static final String ALL_FEES = "from Fee";
		public static final String ALL_ALTERNATIVE_FEE_MAPS = "from AlternativeFeeMap";
	}

	public static final class BUSINESS_LOGIC {
		public static final int YEAR_STARTING_MONTH = 9;
		public static final int YEAR_ENDING_MONTH = 5;

		public static final int FEMALE = 0;
		public static final int MALE = 1;
	}

	public static final class MENU_ITEM {
		public static final String ALTERNATIVE_FEE_MAP = "AlternativeFeeChargeMap";
		public static final String COURSE = "Course";
		public static final String CLASS = "Classes";
		public static final String DASHBOARD = "Dashboard";
		public static final String FEE = "Fee";
		public static final String FEE_GROUP = "FeeGroup";
		public static final String FEE_MAP = "FeeMap";
		public static final String FEE_POLICY = "FeePolicy";
		public static final String MONTH = "Month";
		public static final String PARENT = "Parent";
		public static final String PAYMENT = "Payment";
		public static final String STUDENT = "Student";
	}
}
