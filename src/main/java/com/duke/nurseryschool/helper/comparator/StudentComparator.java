package com.duke.nurseryschool.helper.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.hibernate.bean.Student;

public class StudentComparator implements Comparator<Student>, Serializable {

	private static final long serialVersionUID = 4554471513950602094L;

	@Override
	public int compare(Student student1, Student student2) {
		return this.sortAscending(student1, student2);
	}

	private int sortAscending(Student student1, Student student2) {
		String[] reversedFragments1 = Helper.extractAndReverseNameFragments(student1);
		String[] reversedFragments2 = Helper.extractAndReverseNameFragments(student2);

		return Helper.compareNames(reversedFragments1, reversedFragments2);
	}

	private int sortDescending(Student student1, Student student2) {
		return this.sortAscending(student2, student1);
	}

}
