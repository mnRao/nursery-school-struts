package com.duke.nurseryschool.helper;

public final class Constant {
	public static final String EMPTY_STRING = "";
	public static final String SPACE = " ";
	public static final String ZERO = "0";

	public static final class PUNCTUATION_MARK {
		public static final String BRACKET_OPEN = "[";
		public static final String BRACKET_CLOSE = "]";
		public static final String PARENTHESIS_OPEN = "(";
		public static final String PARENTHESIS_CLOSE = ")";
		public static final String HYPHEN = "-";
	}

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

		public static final String SUCCESS_RECORD_CREATE_UPDATE = "success.model.create";
		public static final String FORM_GENDER_MALE = "form.gender.male";
		public static final String FORM_GENDER_FEMALE = "form.gender.female";

		public static final String EXCEL_HEADER_PAYMENT = "excel.header.top.payment";
		public static final String EXCEL_HEADER_MONTH = "excel.header.top.month";
		public static final String EXCEL_HEADER_YEAR = "excel.header.top.year";
		public static final String EXCEL_HEADER_CLASS = "excel.header.top.class";
		public static final String EXCEL_HEADER_DAYS = "excel.header.top.availableDays";
	}

	public static final class DATABASE_QUERY {
		public static final String ALL_CLASSES = "from Classes";
		public static final String ALL_COURSES = "from Course";
		public static final String ALL_EXTRA_FEE_TYPES = "from ExtraFeeType";
		public static final String ALL_EXTRA_FEE_MAPS = "from ExtraFeeMap";
		public static final String ALL_FEE_DETAILS = "from FeeDetails";
		public static final String ALL_FEE_POLICIES = "from FeePolicy";
		public static final String ALL_MONTHS = "from Month";
		public static final String ALL_PARENTS = "from Parent";
		public static final String ALL_PAYMENTS = "from Payment";
		public static final String ALL_STUDENTS = "from Student";
		public static final String ALL_SUBJECTS = "from Subject";
		public static final String ALL_SUBJECT_FEE_MAPS = "from SubjectFeeMap";
	}

	public static final class BUSINESS_LOGIC {
		public static final int YEAR_STARTING_MONTH = 9;
		public static final int YEAR_ENDING_MONTH = 5;

		public static final int FEMALE = 0;
		public static final int MALE = 1;
	}

	public static final class MENU_ITEM {
		public static final String DASHBOARD = "Dashboard";
		public static final String COURSE = "Course";
		public static final String CLASS = "Class";
		public static final String STUDENT = "Student";
		public static final String PARENT = "Parent";
		public static final String SUBJECT = "Subject";
		public static final String EXTRA_FEE_TYPE = "ExtraFeeType";
		public static final String MONTH = "Month";
		public static final String FEE_POLICY = "FeePolicy";
		public static final String FEE_DETAILS = "FeeDetails";
		public static final String EXTRA_FEE_MAP = "ExtraFeeMap";
		public static final String SUBJECT_FEE_MAP = "SubjectFeeMap";
		public static final String PAYMENT = "Payment";
	}
}
