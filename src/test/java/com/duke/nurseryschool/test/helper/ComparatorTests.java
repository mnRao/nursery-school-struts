package com.duke.nurseryschool.test.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import com.duke.nurseryschool.helper.comparator.StudentComparator;
import com.duke.nurseryschool.hibernate.bean.Student;

public class ComparatorTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCompareStudent() {
		Student student1 = new Student();
		student1.setStudentId(1);
		student1.setName("Trần Lâm Thùy Anh");

		Student student2 = new Student();
		student2.setStudentId(2);
		student2.setName("Hà Vũ Đức Anh");

		Student student3 = new Student();
		student3.setStudentId(3);
		student3.setName("Nguyễn Châu Anh");

		Student student4 = new Student();
		student4.setStudentId(4);
		student4.setName("Trần Đình Việt Anh");

		// Add all students
		List<Student> students = new ArrayList<>();
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);

		Assert.assertEquals(1, students.get(0).getStudentId());
		Assert.assertEquals(2, students.get(1).getStudentId());
		Assert.assertEquals(3, students.get(2).getStudentId());
		Assert.assertEquals(4, students.get(3).getStudentId());

		Collections.sort(students, new StudentComparator());

		Assert.assertEquals(3, students.get(0).getStudentId());
		Assert.assertEquals(1, students.get(1).getStudentId());
		Assert.assertEquals(4, students.get(2).getStudentId());
		Assert.assertEquals(2, students.get(3).getStudentId());

	}

}
