package com.duke.nurseryschool.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.duke.nurseryschool.helper.comparator.FeeComparator;
import com.duke.nurseryschool.hibernate.bean.AlternativeFeeMap;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeeMap;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.embedded.FeePolicyFee;
import com.duke.nurseryschool.hibernate.bean.embedded.PaymentFee;
import com.opensymphony.xwork2.DefaultTextProvider;
import com.opensymphony.xwork2.TextProvider;

public class BusinessLogicSolver {

	/**
	 * Calculate <i>current</i> grade for students in course
	 */

	public static Grade calculateGrade(int startYear, int endYear) {
		// Base on month actualYear (Ex: Course: [2013-2014]; Current month:
		// 01-2014 => actualYear = 2013
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int currentYear = Helper.calculateCurrentYear();

		return calculateGrade(startYear, endYear, currentMonth, currentYear);
	}

	public static Grade calculateGrade(int startYear, int endYear,
			int currentMonth, int currentYear) {
		Grade grade = Grade.UNIDENTIFIED;
		// Choose grade
		if (currentYear > endYear
				|| (currentYear == endYear && isCurrentMonthPrevPart(currentMonth))) {
			grade = Grade.UNIDENTIFIED;
		}
		else if ((currentYear == endYear - 1 && isCurrentMonthPrevPart(currentMonth))
				|| currentYear == endYear
				&& isCurrentMonthNextPart(currentMonth)) {
			grade = Grade.FIFTH;
		}
		else if ((currentYear == endYear - 2 && isCurrentMonthPrevPart(currentMonth))
				|| currentYear == endYear - 1
				&& isCurrentMonthNextPart(currentMonth)) {
			grade = Grade.FOURTH;
		}
		else if ((currentYear == endYear - 3 && isCurrentMonthPrevPart(currentMonth))
				|| currentYear == endYear - 2
				&& isCurrentMonthNextPart(currentMonth)) {
			grade = Grade.THIRD;
		}
		else if ((currentYear == endYear - 4 && isCurrentMonthPrevPart(currentMonth))
				|| currentYear == endYear - 3
				&& isCurrentMonthNextPart(currentMonth)) {
			grade = Grade.SECOND;
		}

		return grade;
	}

	private static boolean isCurrentMonthNextPart(int currentMonth) {
		return currentMonth < Constant.BUSINESS_LOGIC.YEAR_STARTING_MONTH;
	}

	private static boolean isCurrentMonthPrevPart(int currentMonth) {
		return currentMonth >= Constant.BUSINESS_LOGIC.YEAR_STARTING_MONTH;
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
		// Month starting at 0 (from January)
		// => plus 1 to compare
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int currentYear = Helper.calculateCurrentYear();
		return calculateCurrentAcademicYear(currentMonth, currentYear);
	}

	public static String calculateCurrentAcademicYear(int currentMonth,
			int currentYear) {
		int startingYear, endingYear;
		// Check current month to define starting year
		if (currentMonth >= 9 && currentMonth <= 12) {
			startingYear = currentYear;
		}
		else {
			startingYear = currentYear - 1;
		}
		endingYear = startingYear + 1;

		StringBuffer academicYear = new StringBuffer();
		academicYear.append(Constant.PUNCTUATION_MARK.BRACKET_SQUARE_OPEN)
				.append(startingYear).append(Constant.PUNCTUATION_MARK.HYPHEN)
				.append(endingYear)
				.append(Constant.PUNCTUATION_MARK.BRACKET_SQUARE_CLOSE);

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

	/* Calculate fee amount base on inherent and overriding items */
	public static double calculateFeeAmount(Session session, Fee fee,
			FeePolicy feePolicy, Payment payment) {
		// TODO Refactor this method
		double amount = 0;
		FeeType type = fee.getType();
		AlternativeFeeMap alternativeFeeMap = (AlternativeFeeMap) session.get(
				AlternativeFeeMap.class, new PaymentFee(payment, fee));
		boolean isAlternativeFeeMapNull = (alternativeFeeMap == null);
		boolean isAlternativeAmountNull = true;
		if (!isAlternativeFeeMapNull) {
			isAlternativeAmountNull = alternativeFeeMap.getAlternativeAmount() == null;
		}
		//
		if (type == FeeType.ALL_EXCEPT_SELECTED || type == FeeType.STATIC) {
			// If AlternativeFeeMap null or exists but contains no value then
			// find in FeeMap
			// if (isAlternativeFeeMapNull && isAlternativeAmountNull){
			if (isAlternativeFeeMapNull
					|| (!isAlternativeFeeMapNull && isAlternativeAmountNull)) {
				amount = getAmountFromFeeMap(session, fee, feePolicy);
			}
			else {
				amount = alternativeFeeMap.getAlternativeAmount().doubleValue();
			}
		}
		else if (type == FeeType.SELECTED_ONLY) {
			// Check whether alternative fee exists, if no then 0
			if (isAlternativeFeeMapNull) {
				amount = 0;
			}
			else {
				if (isAlternativeAmountNull) {
					amount = getAmountFromFeeMap(session, fee, feePolicy);
				}
				else {
					amount = alternativeFeeMap.getAlternativeAmount()
							.doubleValue();
				}
			}
		}

		return amount;// FeeType.UNKNOWN
	}

	private static double getAmountFromFeeMap(Session session, Fee fee,
			FeePolicy feePolicy) {
		double amount;
		FeeMap feeMap = (FeeMap) session.get(FeeMap.class, new FeePolicyFee(
				feePolicy, fee));
		amount = (feeMap == null) ? 0 : feeMap.getAmount().doubleValue();
		return amount;
	}

	public static HashSet<Fee> sortFeeSet(Set<Fee> set) {
		List<Fee> feeList = new ArrayList<Fee>(set);
		Collections.sort(feeList, new FeeComparator());
		HashSet<Fee> sortedSet = new HashSet<Fee>(feeList);
		return sortedSet;
	}

}
