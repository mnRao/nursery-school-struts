package com.duke.nurseryschool.helper.comparator;

import java.util.Comparator;

import com.duke.nurseryschool.hibernate.bean.Student;

public class StudentComparator implements Comparator<Student> {

	@Override
	public int compare(Student student1, Student student2) {
		return this.sortAscending(student1, student2);
	}

	private int sortAscending(Student student1, Student student2) {
		return student1.getName().compareTo(student2.getName());
	}

	private int sortDescending(Student student1, Student student2) {
		return student2.getName().compareTo(student1.getName());
	}
}
