package com.duke.nurseryschool.helper.date;

import java.util.Calendar;

import com.duke.nurseryschool.helper.Helper;

public class CurrentCalendar implements ICurrentCalendar {
	private int currentMonth;
	private int currentYear;

	public CurrentCalendar() {
		this.calculateCurrentMonthAndYear();
	}

	@Override
	public void calculateCurrentMonthAndYear() {
		this.currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		this.currentYear = Helper.calculateCurrentYear();
	}

	@Override
	public int getCurrentMonth() {
		return this.currentMonth;
	}

	@Override
	public int getCurrentYear() {
		return this.currentYear;
	}

}
