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
			students = this.session
					.createQuery(Constant.DATABASE_QUERY.ALL_STUDENTS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	public Student getStudent(int studentId) {
		Student student = null;
		try {
			student = (Student) this.session.get(Student.class, studentId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	public void saveOrUpdateStudent(Student student) {
		try {
			this.session.saveOrUpdate(student);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public void deleteStudent(int studentId) {
		try {
			Student student = (Student) this.session.get(Student.class,
					studentId);
			this.session.delete(student);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}
}
