package com.duke.nurseryschool.helper;

public final class Constant {
	public static final String EMPTY_STRING = "";
	public static final String SPACE = " ";
	public static final String ZERO = "0";

	public static final String SESSION_USER = "USER";
	public static final int COOKIE_TIME_OUT = 60 * 1;

	public static final String EXCEL_SUFFIX = ".xls";

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
		public static final String LOGIN_REMEMBER_ME = "rememberMe";
		public static final String COOKIE_USERNAME = "userName";
	}

	// TODO
	// When modifying key in global.properties => must also modify this class
	// appropriately
	public static final class I18N {
		public static final String ERROR_NO_PAYMENT_APPLIED = "error.no.payment.applied";
		public static final String ERROR_LOGIN_USERNAME = "error.login.username";
		public static final String ERROR_LOGIN_PASSWORD = "error.login.password";

		public static final String ERROR_CONSTRAINT_ALTERNATIVEFEEMAP_ALTERNATIVEAMOUNT = "errors.constraint.alternativeFeeMap.alternativeAmount";
		public static final String ERROR_CONSTRAINT_COURSE_YEARS = "errors.constraint.course.years";
		public static final String ERROR_CONSTRAINT_COURSE_STARTYEAR = "errors.constraint.course.startYear";
		public static final String ERROR_CONSTRAINT_COURSE_ENDYEAR = "errors.constraint.course.startYear";
		public static final String ERROR_CONSTRAINT_MONTH_MONTHNAME = "errors.constraint.month.monthName";
		public static final String ERROR_CONSTRAINT_MONTH_YEAR = "errors.constraint.month.year";
		public static final String ERROR_CONSTRAINT_FEEMAP_AMOUNT = "errors.constraint.feeMap.amount";
		public static final String ERROR_CONSTRAINT_FEEPOLICY_FEEPERNORMALMEAL = "errors.constraint.feePolicy.feePerNormalMeal";
		public static final String ERROR_CONSTRAINT_FEEPOLICY_TOTALBREAKFASTFEE = "errors.constraint.feePolicy.totalBreakfastFee";
		public static final String ERROR_CONSTRAINT_FEEPOLICY_PENALTYFEEPERBREAKFAST = "errors.constraint.feePolicy.penaltyFeePerBreakfast";
		public static final String ERROR_CONSTRAINT_FEEPOLICY_AVAILABLEDAYS = "errors.constraint.feePolicy.availableDays";
		public static final String ERROR_CONSTRAINT_PAYMENT_ABSENCECOUNT = "errors.constraint.payment.absenceCount";
		public static final String ERROR_REQUIRED_FEE_NAME = "errors.required.fee.name";
		public static final String ERROR_REQUIRED_FEEGROUP_NAME = "errors.required.feeGroup.name";
		public static final String ERROR_REQUIRED_FEEPOLICY_FEEPERNORMALMEAL = "errors.required.feePolicy.feePerNormalMeal";
		public static final String ERROR_REQUIRED_FEEPOLICY_TOTALBREAKFASTFEE = "errors.required.feePolicy.totalBreakfastFee";
		public static final String ERROR_REQUIRED_FEEPOLICY_PENALTYFEEPERBREAKFAST = "errors.required.feePolicy.penaltyFeePerBreakfast";
		public static final String ERROR_REQUIRED_CLASS_COURSEID = "errors.required.class.courseId";
		public static final String ERROR_REQUIRED_CLASS_CODE = "errors.required.class.code";
		public static final String ERROR_REQUIRED_STUDENT_NAME = "errors.required.student.name";

		public static final String SUCCESS_RECORD_CREATE_UPDATE = "success.model.create";
		public static final String FORM_GENDER_MALE = "form.gender.male";
		public static final String FORM_GENDER_FEMALE = "form.gender.female";

		public static final String EXCEL_HEADER_PAYMENT = "excel.header.top.payment";
		public static final String EXCEL_HEADER_MONTH = "excel.header.top.month";
		public static final String EXCEL_HEADER_YEAR = "excel.header.top.year";
		public static final String EXCEL_HEADER_CLASS = "excel.header.top.class";
		public static final String EXCEL_HEADER_DAYS = "excel.header.top.availableDays";

		public static final String EXCEL_FILE_PREFIX_TITLE = "excel.file.prefix_title";
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
		public static final String DASHBOARD = "Dashboard";
		public static final String COURSE = "Course";
		public static final String CLASS = "Class";
		public static final String STUDENT = "Student";
		public static final String PARENT = "Parent";
		public static final String FEE = "Fee";
		public static final String FEE_GROUP = "FeeGroup";
		public static final String MONTH = "Month";
		public static final String FEE_POLICY = "FeePolicy";
		public static final String FEE_DETAILS = "FeeDetails";
		public static final String EXTRA_FEE_MAP = "ExtraFeeMap";
		public static final String ALTERNATIVE_FEE_MAP = "AlternativeFeeMap";
		public static final String PAYMENT = "Payment";
	}
}
