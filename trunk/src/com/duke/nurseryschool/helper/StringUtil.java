package com.duke.nurseryschool.helper;

public final class StringUtil {

	private StringUtil() {

	}

	/* Trim and check if empty */
	public static boolean isEmpty(String input) {
		return (input == null) ? true : isPhysicallyEmpty(input.trim());
	}

	/* Check if empty. Blank character ' ' are considered not empty */
	public static boolean isPhysicallyEmpty(String input) {
		return input == null || input.equals(Constant.EMPTY_STRING);
	}

}
