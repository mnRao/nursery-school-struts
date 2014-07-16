package com.duke.nurseryschool.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.comparator.FeeComparator;
import com.duke.nurseryschool.helper.date.CurrentCalendar;
import com.duke.nurseryschool.helper.date.ICurrentCalendar;
import com.duke.nurseryschool.hibernate.bean.AlternativeFeeMap;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeeMap;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.Student;
import com.duke.nurseryschool.hibernate.bean.embedded.FeePolicyFee;
import com.duke.nurseryschool.hibernate.bean.embedded.PaymentFee;
import com.opensymphony.xwork2.DefaultTextProvider;
import com.opensymphony.xwork2.TextProvider;

public class BusinessLogicSolver {

	private static volatile BusinessLogicSolver instance = new BusinessLogicSolver();

	private ICurrentCalendar currentCalendar;

	private BusinessLogicSolver() {
		this.currentCalendar = new CurrentCalendar();
	}

	public static BusinessLogicSolver getInstance() {
		return instance;
	}

	/**
	 * Calculate <i>current</i> grade for students in course <br/> Base on month
	 * actualYear (Ex: Course: [2013-2014]; Current month: 01-2014 => actualYear
	 * = 2013
	 */
	public Grade calculateGrade(int startYear, int endYear) {
		return this.calculateGrade(startYear, endYear,
				this.currentCalendar.getCurrentMonth(),
				this.currentCalendar.getCurrentYear());
	}

	private Grade calculateGrade(int startYear, int endYear, int currentMonth,
			int currentYear) {
		Grade grade = Grade.UNIDENTIFIED;
		// Choose grade
		if (currentYear > endYear
				|| (currentYear == endYear && this
						.isCurrentMonthPrevPart(currentMonth))) {
			grade = Grade.UNIDENTIFIED;
		}
		else if ((currentYear == endYear - 1 && this
				.isCurrentMonthPrevPart(currentMonth))
				|| currentYear == endYear
				&& this.isCurrentMonthNextPart(currentMonth)) {
			grade = Grade.FIFTH;
		}
		else if ((currentYear == endYear - 2 && this
				.isCurrentMonthPrevPart(currentMonth))
				|| currentYear == endYear - 1
				&& this.isCurrentMonthNextPart(currentMonth)) {
			grade = Grade.FOURTH;
		}
		else if ((currentYear == endYear - 3 && this
				.isCurrentMonthPrevPart(currentMonth))
				|| currentYear == endYear - 2
				&& this.isCurrentMonthNextPart(currentMonth)) {
			grade = Grade.THIRD;
		}
		else if ((currentYear == endYear - 4 && this
				.isCurrentMonthPrevPart(currentMonth))
				|| currentYear == endYear - 3
				&& this.isCurrentMonthNextPart(currentMonth)) {
			grade = Grade.SECOND;
		}

		return grade;
	}

	private boolean isCurrentMonthNextPart(int currentMonth) {
		return currentMonth < Constant.BUSINESS_LOGIC.ACADEMIC_YEAR_STARTING_MONTH;
	}

	private boolean isCurrentMonthPrevPart(int currentMonth) {
		return currentMonth >= Constant.BUSINESS_LOGIC.ACADEMIC_YEAR_STARTING_MONTH;
	}

	/**
	 * Get I18N text for gender
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public String calculateGenderText(int gender)
			throws InstantiationException, IllegalAccessException {
		TextProvider textProvider = DefaultTextProvider.class.newInstance();

		String genderText = Constant.EMPTY_STRING;
		switch (gender) {
			case Constant.BUSINESS_LOGIC.FEMALE:
				genderText = textProvider.getText(I18N.FORM_GENDER_FEMALE);
				break;
			case Constant.BUSINESS_LOGIC.MALE:
				genderText = textProvider.getText(I18N.FORM_GENDER_MALE);
				break;
		}

		return genderText;
	}

	public String getBooleanText(boolean booleanValue)
			throws InstantiationException, IllegalAccessException {
		TextProvider textProvider = DefaultTextProvider.class.newInstance();

		String booleanText = Constant.EMPTY_STRING;
		if (booleanValue) {
			booleanText = textProvider.getText(I18N.FORM_TRUEFALSE_TRUE);
		}
		else {
			booleanText = textProvider.getText(I18N.FORM_TRUEFALSE_FALSE);
		}

		return booleanText;
	}

	/**
	 * Ex: [2012-2013]
	 */
	public String calculateCurrentAcademicYear() {
		// Month starting at 0 (from January)
		// => plus 1 to compare
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int currentYear = Helper.calculateCurrentYear();
		return this.calculateCurrentAcademicYear(currentMonth, currentYear);
	}

	public String calculateCurrentAcademicYear(int currentMonth, int currentYear) {
		int startingYear, endingYear;
		// Check current month to define starting year
		if (currentMonth >= Constant.BUSINESS_LOGIC.ACADEMIC_YEAR_STARTING_MONTH
				&& currentMonth <= Constant.BUSINESS_LOGIC.YEAR_ENDING_MONTH) {
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
	public String getStandardMonthName(int monthName) {
		if (monthName < Constant.BUSINESS_LOGIC.YEAR_STARTING_MONTH
				|| monthName > Constant.BUSINESS_LOGIC.YEAR_ENDING_MONTH)
			throw new IllegalArgumentException();
		else if (monthName > 9)
			return Integer.toString(monthName); // 10 -> 12
		else
			return Constant.ZERO + monthName; // 01 -> 09
	}

	/* Calculate fee amount base on inherent and overriding items */
	public double calculateFeeAmount(Session session, Fee fee,
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
		if (type == FeeType.ALL_EXCEPT_SELECTED) {
			// If AlternativeFeeMap null or exists but contains no value then
			// find in FeeMap
			// if (isAlternativeFeeMapNull && isAlternativeAmountNull){
			if (isAlternativeFeeMapNull
					|| (!isAlternativeFeeMapNull && isAlternativeAmountNull)) {
				amount = this.getAmountFromFeeMap(session, fee, feePolicy);
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
					amount = this.getAmountFromFeeMap(session, fee, feePolicy);
				}
				else {
					amount = alternativeFeeMap.getAlternativeAmount()
							.doubleValue();
				}
			}
		}

		return amount;// FeeType.UNKNOWN
	}

	private double getAmountFromFeeMap(Session session, Fee fee,
			FeePolicy feePolicy) {
		double amount;
		FeeMap feeMap = (FeeMap) session.get(FeeMap.class, new FeePolicyFee(
				feePolicy, fee));
		amount = (feeMap == null || feeMap.getAmount() == null) ? 0 : feeMap
				.getAmount().doubleValue();
		return amount;
	}

	public HashSet<Fee> sortFeeSet(Set<Fee> set) {
		List<Fee> feeList = new ArrayList<Fee>(set);
		Collections.sort(feeList, new FeeComparator());
		HashSet<Fee> sortedSet = new HashSet<Fee>(feeList);
		return sortedSet;
	}

	public void setCurrentCalendar(ICurrentCalendar currentCalendar) {
		this.currentCalendar = currentCalendar;
	}

	public Set<Student> filterActiveStudents(Set<Student> students) {
		Set<Student> activeStudents = new HashSet<Student>();
		for (Student student : students) {
			if (student.isActive()) {
				activeStudents.add(student);
			}
		}
		return activeStudents;
	}
}
