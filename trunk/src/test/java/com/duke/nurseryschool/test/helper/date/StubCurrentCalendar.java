package com.duke.nurseryschool.test.helper.date;

import com.duke.nurseryschool.helper.date.ICurrentCalendar;

public class StubCurrentCalendar implements ICurrentCalendar {

	private final int currentMonth;
	private final int currentYear;

	public StubCurrentCalendar(int currentMonth, int currentYear) {
		this.currentMonth = currentMonth;
		this.currentYear = currentYear;
	}

	@Override
	public void calculateCurrentMonthAndYear() {
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
