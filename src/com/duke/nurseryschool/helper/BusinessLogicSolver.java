package com.duke.nurseryschool.helper;

import java.util.Calendar;

import com.opensymphony.xwork2.DefaultTextProvider;
import com.opensymphony.xwork2.TextProvider;

public class BusinessLogicSolver {

	/**
	 * Calculate <i>current</i> grade for students in course
	 */
	public static Grade calculateGrade(int startYear, int endYear) {
		Grade grade = Grade.GRADUATED;

		// Base on month actualYear (Ex: Course: [2013-2014]; Current month:
		// 01-2014 => actualYear = 2013
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int actualYear = Helper.calculateCurrentYear();
		if (currentMonth < 9) {
			actualYear = actualYear - 1;
		}

		// Choose grade
		if (actualYear > endYear) {
			grade = Grade.GRADUATED;
		}
		else if (actualYear == endYear) {
			grade = Grade.FIFTH;
		}
		else if (actualYear == startYear + 1) {
			grade = Grade.THIRD;
		}
		else if (actualYear == startYear + 2) {
			grade = Grade.FOURTH;
		}
		else if (actualYear <= startYear) {
			grade = Grade.SECOND;
		}

		return grade;
	}

	/**
	 * Get I18N text for gender
	 */
	public static String calculateGenderText(int gender)
			throws InstantiationException, IllegalAccessException {
		TextProvider textProvider = DefaultTextProvider.class.newInstance();

		String genderText = Constant.EMPTY_STRING;
		switch (gender) {
			case Constant.BUSINESS_LOGIC.FEMALE:
				genderText = textProvider
						.getText(Constant.I18N.FORM_GENDER_FEMALE);
				break;
			case Constant.BUSINESS_LOGIC.MALE:
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

	/**
	 * Add extra ZERO symbol as prefix for month name. Ex: 1 => 01
	 */
	public static String getStandardMonthName(int monthName) {
		if (monthName > 9)
			return Integer.toString(monthName);
		else
			return Constant.ZERO + monthName;
	}
}
