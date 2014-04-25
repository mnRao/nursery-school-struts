package com.duke.nurseryschool.helper.comparator;

import java.util.Comparator;

import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.hibernate.bean.Student;

public class StudentComparator implements Comparator<Student> {

	private String studentName1;
	private String studentName2;
	private String filterName1;
	private String filterName2;

	@Override
	public int compare(Student student1, Student student2) {
		return this.sortAscending(student1, student2);
	}

	private int sortAscending(Student student1, Student student2) {
		this.extractInfo(student1, student2);
		return this.filterName1.compareTo(this.filterName2);
	}

	private int sortDescending(Student student1, Student student2) {
		this.extractInfo(student1, student2);
		return student2.getName().compareTo(student1.getName());
	}

	private void extractInfo(Student student1, Student student2) {
		this.studentName1 = student1.getName();
		this.studentName2 = student2.getName();
		this.filterName1 = Helper.extractLastWord(this.studentName1);
		this.filterName2 = Helper.extractLastWord(this.studentName2);
	}
}
