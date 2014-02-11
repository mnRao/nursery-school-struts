package com.duke.nurseryschool.helper.comparator;

import java.util.Comparator;

import com.duke.nurseryschool.hibernate.bean.Month;

public class MonthComparator implements Comparator<Month> {

	@Override
	public int compare(Month month1, Month month2) {
		return this.sortDescending(month1, month2);
	}

	private int sortAscending(Month month1, Month month2) {
		return month1.getLabel().compareTo(month2.getLabel());
	}

	private int sortDescending(Month month1, Month month2) {
		return month2.getLabel().compareTo(month1.getLabel());
	}
}
