package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Course;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class CourseDAO {
	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	public List<Course> getCourses() {
		List<Course> courses = new ArrayList<Course>();
		try {
			courses = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_COURSES).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}

	public void addCourse(Course course) {
		this.session.save(course);
	}
}
