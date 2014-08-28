package com.duke.nurseryschool.helper.excel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.duke.nurseryschool.helper.comparator.StudentComparator;
import com.duke.nurseryschool.hibernate.bean.Student;

import jxl.write.WritableWorkbook;

public class StudentExcelManager extends ExcelManager {

	protected final Map<String, List<Student>> studentsByClasses;

	public StudentExcelManager(WritableWorkbook workbook, List<Student> students) {
		super(workbook);
		this.studentsByClasses = new TreeMap<String, List<Student>>();
	}

	protected void populateAndSortData(List<Student> students) {
		Set<String> unsortedClasses = new TreeSet<String>();
		// Populate all related classes
		for (Student student : students) {
			unsortedClasses.add(student.getAssociatedClass().getCurrentName());
		}
		// Sort classes
		List<String> sortedClasses = new ArrayList<>(unsortedClasses);
		Collections.sort(sortedClasses);
		// Populate into class-student hash map
		for (String className : sortedClasses) {
			List<Student> studentByClass = new ArrayList<Student>();
			for (Student student : students) {
				if (className.equals(student.getAssociatedClass().getCurrentName())) {
					studentByClass.add(student);
				}
			}
			// Sort student names
			Collections.sort(studentByClass, new StudentComparator());
			// Add to map
			this.studentsByClasses.put(className, studentByClass);
		}
	}
}
