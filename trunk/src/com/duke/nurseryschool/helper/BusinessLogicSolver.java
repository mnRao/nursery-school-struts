package com.duke.nurseryschool.helper;

import java.util.Calendar;
import java.util.Set;

import org.hibernate.Session;

import com.duke.nurseryschool.hibernate.HibernateUtil;
import com.duke.nurseryschool.hibernate.bean.ExtraFeeMap;
import com.duke.nurseryschool.hibernate.bean.FeeDetails;
import com.duke.nurseryschool.hibernate.bean.SubjectFeeMap;
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

	public static void recalculateExtraStudyFee(int feeDetailsId,
			Session session) {
		FeeDetails feeDetails = (FeeDetails) session.get(FeeDetails.class,
				Integer.valueOf(feeDetailsId));
		feeDetails = recalculateExtraStudyFee(feeDetails, session);
		session.saveOrUpdate(feeDetails);
	}

	private static FeeDetails recalculateExtraStudyFee(FeeDetails feeDetails,
			Session session) {
		// Calculate total study fee for all subjects
		Set<SubjectFeeMap> allSubjectFeeMaps = feeDetails.getSubjectFeeMaps();
		double totalStudyFee = 0;
		for (SubjectFeeMap cSubjectFeeMap : allSubjectFeeMaps) {
			totalStudyFee += cSubjectFeeMap.getAmount();
		}
		feeDetails.setTotalExtraStudyFee(totalStudyFee);

		return feeDetails;
	}

	public static void recalculateExtraFee(int feeDetailsId, Session session) {
		FeeDetails feeDetails = (FeeDetails) session.get(FeeDetails.class,
				Integer.valueOf(feeDetailsId));
		feeDetails = recalculateExtraFee(feeDetails, session);
		session.saveOrUpdate(feeDetails);
	}

	private static FeeDetails recalculateExtraFee(FeeDetails feeDetails,
			Session session) {
		// Calculate total study fee for all subjects
		Set<ExtraFeeMap> allExtraFeeMaps = feeDetails.getExtraFeeMaps();
		double totalExtraFee = 0;
		for (ExtraFeeMap cExtraFeeMap : allExtraFeeMaps) {
			totalExtraFee += cExtraFeeMap.getAmount();
		}
		feeDetails.setTotalExtraFee(totalExtraFee);

		return feeDetails;
	}
}
