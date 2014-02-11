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
		int currentYear = Helper.calculateCurrentYear();
		int actualYear = currentYear;
		if (currentMonth < 9) {
			actualYear = currentYear - 1;
		}

		// Choose grade
		if (currentYear > endYear
				|| (currentYear == endYear && currentMonth > 9)) {
			grade = Grade.GRADUATED;
		}
		else if ((currentYear == endYear - 1 && currentMonth > 9)
				|| currentYear == endYear && currentMonth < 9) {
			grade = Grade.FIFTH;
		}
		else if ((currentYear == endYear - 2 && currentMonth > 9)
				|| currentYear == endYear - 1 && currentMonth < 9) {
			grade = Grade.THIRD;
		}
		else if ((currentYear == endYear - 3 && currentMonth > 9)
				|| currentYear == endYear - 2 && currentMonth < 9) {
			grade = Grade.FOURTH;
		}
		else if ((currentYear == endYear - 4 && currentMonth > 9)
				|| currentYear == endYear - 3 && currentMonth < 9) {
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

	// public static void recalculateExtraStudyFee(int feeDetailsId,
	// Session session) {
	// FeeDetails feeDetails = (FeeDetails) session.get(FeeDetails.class,
	// Integer.valueOf(feeDetailsId));
	// feeDetails = recalculateExtraStudyFee(feeDetails, session);
	// session.saveOrUpdate(feeDetails);
	// }
	//
	// private static FeeDetails recalculateExtraStudyFee(FeeDetails feeDetails,
	// Session session) {
	// // Calculate total study fee for all subjects
	// Set<AlternativeFeeMap> allSubjectFeeMaps =
	// feeDetails.getSubjectFeeMaps();
	// double totalStudyFee = 0;
	// for (AlternativeFeeMap cSubjectFeeMap : allSubjectFeeMaps) {
	// totalStudyFee += cSubjectFeeMap.getAmount();
	// }
	// feeDetails.setTotalExtraStudyFee(totalStudyFee);
	//
	// return feeDetails;
	// }
	//
	// public static void recalculateExtraFee(int feeDetailsId, Session session)
	// {
	// FeeDetails feeDetails = (FeeDetails) session.get(FeeDetails.class,
	// Integer.valueOf(feeDetailsId));
	// feeDetails = recalculateExtraFee(feeDetails, session);
	// session.saveOrUpdate(feeDetails);
	// }
	//
	// private static FeeDetails recalculateExtraFee(FeeDetails feeDetails,
	// Session session) {
	// // Calculate total study fee for all subjects
	// Set<FeeMap> allExtraFeeMaps = feeDetails.getExtraFeeMaps();
	// double totalExtraFee = 0;
	// for (FeeMap cExtraFeeMap : allExtraFeeMaps) {
	// totalExtraFee += cExtraFeeMap.getAmount();
	// }
	// feeDetails.setTotalExtraFee(totalExtraFee);
	//
	// return feeDetails;
	// }
}
