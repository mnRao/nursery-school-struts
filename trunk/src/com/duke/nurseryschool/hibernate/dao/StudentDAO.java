package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Student;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class StudentDAO {

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		try {
			students = session
					.createQuery(Constant.DATABASE_QUERY.ALL_STUDENTS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	public void addStudent(Student student) {
		session.save(student);
	}
}
