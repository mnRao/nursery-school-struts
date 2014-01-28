package com.duke.nurseryschool.helper;

import java.util.Calendar;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.DefaultTextProvider;
import com.opensymphony.xwork2.TextProvider;

public class Helper {

	public static String calculateGenderText(int gender)
			throws InstantiationException, IllegalAccessException {
		TextProvider textProvider = DefaultTextProvider.class.newInstance();

		String genderText = Constant.EMPTY_STRING;
		switch (gender) {
			case 0:
				genderText = textProvider
						.getText(Constant.I18N.FORM_GENDER_FEMALE);
				break;
			case 1:
				genderText = textProvider
						.getText(Constant.I18N.FORM_GENDER_MALE);
				break;
		}

		return genderText;
	}

	/**
	 * Ex: [2012-2013]
	 */
	public static String calculateCurrentAcademicYear() {
		int startingYear, endingYear;
		// Month starting at 0 (from January)
		// => plus 1 to compare
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int currentYear = Helper.calculateCurrentYear();
		// Check current month to define starting year
		if (currentMonth >= 9 && currentMonth <= 12) {
			startingYear = currentYear;
		}
		else {
			startingYear = currentYear - 1;
		}
		endingYear = startingYear + 1;

		StringBuffer academicYear = new StringBuffer();
		academicYear.append(Constant.PUNCTUATION_MARK.BRACKET_OPEN)
				.append(startingYear).append(Constant.PUNCTUATION_MARK.HYPHEN)
				.append(endingYear)
				.append(Constant.PUNCTUATION_MARK.BRACKET_CLOSE);

		return academicYear.toString();
	}

	public static int calculateCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * Check whether mapping key is allowed to be modified
	 */
	public static boolean changingMappingKeyAllowed() {
		if (getActionName().contains("edit"))
			return false;
		else
			return true;
	}

	private static String getActionName() {
		return ActionContext.getContext().getName();
	}

	/**
	 * Compute CSS class for given tab (header | content), regarding tab number
	 */
	public static String getTabCss(int tabNumber, boolean isContent) {
		String actionName = ActionContext.getContext().getName();
		boolean isSpecialCase = actionName.contains("edit")
				|| actionName.contains("autoSet");
		String cssClass = calculateTabCss(tabNumber, isSpecialCase, isContent);

		return cssClass;
	}

	private static String calculateTabCss(int tabNumber, boolean isSpecialCase,
			boolean isContent) {
		String cssClass = Constant.EMPTY_STRING;
		if ((isSpecialCase && tabNumber == 2)
				|| (!isSpecialCase && tabNumber == 1)) {
			cssClass = isContent ? "active in" : "active";
		}

		return cssClass;
	}

}
