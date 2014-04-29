package com.duke.nurseryschool.helper;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.opensymphony.xwork2.Action;
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
		if (getActionName().contains(Constant.TAG.ACTION_PREFIX_EDIT))
			return false;
		else
			return true;
	}

	/**
	 * Check whether fee policy key could be edited
	 */
	public static boolean changingFeePolicyAllowed() {
		if (getActionName().equals("autoSetFeePolicyPayment"))
			return false;
		else
			return changingMappingKeyAllowed();
	}

	private static String getActionName() {
		return ActionContext.getContext().getName();
	}

	public static String isMenuItemActive(String menuItem) {
		String currentActionName = getActionName();
		if (currentActionName.endsWith(menuItem))
			return Constant.UI.CURRENT;
		else
			return Constant.EMPTY_STRING;
	}

	public static String isSubMenuItemActive() {
		String currentActionName = getActionName();
		String opened = Constant.UI.OPENED;

		String[] menuItems = {
				Constant.MENU_ITEM.MONTH, Constant.MENU_ITEM.FEE_POLICY,
				Constant.MENU_ITEM.FEE_GROUP, Constant.MENU_ITEM.FEE,
				Constant.MENU_ITEM.FEE_MAP, Constant.MENU_ITEM.PAYMENT,
				Constant.MENU_ITEM.ALTERNATIVE_FEE_MAP,
		};
		for (String item : menuItems) {
			if (currentActionName.endsWith(item)) {
				return opened;
			}
		}
		return Constant.EMPTY_STRING;
	}

	/**
	 * Compute CSS class for given tab (header | content), regarding tab number
	 */
	public static String getTabCss(int tabNumber, boolean isContent,
			String resultCode) {
		String actionName = ActionContext.getContext().getName();

		// Cases for necessary hidden tab
		if (isInvisible(tabNumber, actionName))
			return Constant.UI.APP_HIDDEN;

		boolean isSpecialCase = actionName
				.contains(Constant.TAG.ACTION_PREFIX_EDIT)
				|| actionName.contains(Constant.TAG.ACTION_PREFIX_AUTOSET)
				|| resultCode.equalsIgnoreCase(Action.INPUT);
		String cssClass = calculateTabCss(tabNumber, isSpecialCase, isContent);

		return cssClass;
	}

	private static String calculateTabCss(int tabNumber, boolean isSpecialCase,
			boolean isContent) {
		String cssClass = Constant.EMPTY_STRING;
		if ((isSpecialCase && tabNumber == 2)
				|| (!isSpecialCase && tabNumber == 1)) {
			cssClass = isContent ? Constant.UI.ACTIVE_IN : Constant.UI.ACTIVE;
		}

		return cssClass;
	}

	/**
	 * Prevent from editing directly. Only allow create in auto-set mode
	 */
	private static boolean isInvisible(int tabNumber, String actionName) {
		if (tabNumber == 2 && actionName.contains("Parent")
				&& !actionName.contains(Constant.TAG.ACTION_PREFIX_AUTOSET)
				&& !actionName.contains(Constant.TAG.ACTION_PREFIX_EDIT))
			return true;
		else
			return false;
	}

	public static String getI18N(String key) {
		TextProvider textProvider = null;
		try {
			textProvider = DefaultTextProvider.class.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return Constant.EMPTY_STRING;
		}
		return textProvider.getText(key);
	}

	public static String getI18N(String key, String[] args) {
		TextProvider textProvider = null;
		try {
			textProvider = DefaultTextProvider.class.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return Constant.EMPTY_STRING;
		}
		return textProvider.getText(key, args);
	}

	public static String getDateFormatPlaceHolder() {
		return "dd-MM-YYYY";
	}

	public static int calculateTotalDaysInMonth(int year, int month) {
		int monthOfficialNumber = Integer.MIN_VALUE;
		switch (month) {

			case 1:
				monthOfficialNumber = Calendar.JANUARY;
				break;
			case 2:
				monthOfficialNumber = Calendar.FEBRUARY;
				break;
			case 3:
				monthOfficialNumber = Calendar.MARCH;
				break;
			case 4:
				monthOfficialNumber = Calendar.APRIL;
				break;
			case 5:
				monthOfficialNumber = Calendar.MAY;
				break;
			case 6:
				monthOfficialNumber = Calendar.JUNE;
				break;
			case 7:
				monthOfficialNumber = Calendar.JULY;
				break;
			case 8:
				monthOfficialNumber = Calendar.AUGUST;
				break;
			case 9:
				monthOfficialNumber = Calendar.SEPTEMBER;
				break;
			case 10:
				monthOfficialNumber = Calendar.OCTOBER;
				break;
			case 11:
				monthOfficialNumber = Calendar.NOVEMBER;
				break;
			case 12:
				monthOfficialNumber = Calendar.DECEMBER;
				break;
		}

		Calendar mycal = new GregorianCalendar(year, monthOfficialNumber, 1);
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

		return daysInMonth;
	}

	public static String extractLastWord(String original) {
		// Trim first
		original = original.trim();

		int lastSpace = original.lastIndexOf(Constant.SPACE);
		String filtered = original.substring(lastSpace + 1, original.length());

		return filtered;
	}
}
