package com.duke.nurseryschool.helper.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.duke.nurseryschool.hibernate.bean.Classes;

public class ClassComparator implements Comparator<Classes>, Serializable {

	private static final long serialVersionUID = -3475326909758258964L;

	/**
	 * Compare by label (actually by course starting year)
	 */
	@Override
	public int compare(Classes class1, Classes class2) {
		return this.sortDescending(class1, class2); // Latest first
	}

	private int sortAscending(Classes class1, Classes class2) {
		return class1.getLabel().compareTo(class2.getLabel());
	}

	private int sortDescending(Classes class1, Classes class2) {
		return class2.getLabel().compareTo(class1.getLabel());
	}
}
