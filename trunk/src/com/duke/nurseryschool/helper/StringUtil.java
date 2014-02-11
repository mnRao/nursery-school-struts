package com.duke.nurseryschool.helper;

public final class StringUtil {

	private StringUtil() {

	}

	public static boolean isEmpty(String input) {
		return input == null || input.equals(Constant.EMPTY_STRING);
	}

}
