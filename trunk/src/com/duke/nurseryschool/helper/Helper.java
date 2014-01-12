package com.duke.nurseryschool.helper;

import java.util.Calendar;

public class Helper {

	public static int calculateCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
}
