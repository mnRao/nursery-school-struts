package com.duke.nurseryschool.helper;

import java.util.Calendar;
import java.util.List;

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
		if (currentActionName.equalsIgnoreCase(menuItem))
			return Constant.UI.CURRENT;
		else
			return Constant.EMPTY_STRING;
	}

	public static String isSubMenuItemActive() {
		String currentActionName = getActionName();
		String opened = Constant.UI.OPENED;
		switch (currentActionName) {
			case Constant.MENU_ITEM.MONTH:
			case Constant.MENU_ITEM.FEE_POLICY:
			case Constant.MENU_ITEM.FEE_GROUP:
			case Constant.MENU_ITEM.FEE:
			case Constant.MENU_ITEM.FEE_MAP:
			case Constant.MENU_ITEM.PAYMENT:
			case Constant.MENU_ITEM.ALTERNATIVE_FEE_MAP:
				return opened;
			default:
				return Constant.EMPTY_STRING;
		}
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
}
