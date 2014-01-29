package com.duke.nurseryschool.helper.comparator;

import java.util.Comparator;

import com.duke.nurseryschool.hibernate.bean.Month;

public class MonthComparator implements Comparator<Month> {

	@Override
	public int compare(Month o1, Month o2) {
		return o1.getLabel().compareTo(o2.getLabel());
	}

}
