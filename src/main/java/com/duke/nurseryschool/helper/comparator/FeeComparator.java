package com.duke.nurseryschool.helper.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.duke.nurseryschool.hibernate.bean.Fee;

public class FeeComparator implements Comparator<Fee>, Serializable {

	private static final long serialVersionUID = 3688201265371570767L;

	@Override
	public int compare(Fee fee1, Fee fee2) {
		return this.sortAscending(fee1, fee2);
	}

	private int sortAscending(Fee fee1, Fee fee2) {
		return fee1.getName().compareTo(fee2.getName());
	}

	private int sortDescending(Fee fee1, Fee fee2) {
		return fee2.getName().compareTo(fee1.getName());
	}
}
