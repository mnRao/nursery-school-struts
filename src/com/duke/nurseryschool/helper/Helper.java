package com.duke.nurseryschool.helper;

import java.util.Calendar;

import com.opensymphony.xwork2.ActionContext;

public class Helper {

	public static int calculateCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * Compute CSS class for given tab (header | content), regarding tab number
	 */
	public static String getTabCss(int tabNumber, boolean isContent) {
		String actionName = ActionContext.getContext().getName();
		boolean isSpecialCase = actionName.equals("editSubject");
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
