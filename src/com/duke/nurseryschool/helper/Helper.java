package com.duke.nurseryschool.helper;

import java.util.Calendar;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.DefaultTextProvider;
import com.opensymphony.xwork2.TextProvider;

public class Helper {

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

	public static String isMenuItemActive(String menuItem) {
		String currentActionName = getActionName();
		if (currentActionName.contains(menuItem))
			return "current";
		else
			return Constant.EMPTY_STRING;
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

	public static String getI18N(String key) {
		TextProvider textProvider = null;
		try {
			textProvider = DefaultTextProvider.class.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return textProvider.getText(key);
	}
}
