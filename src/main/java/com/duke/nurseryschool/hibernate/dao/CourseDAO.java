package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Course;

public class CourseDAO extends CoreDAO {

	@SuppressWarnings("unchecked")
	public List<Course> getCourses() {
		List<Course> courses = new ArrayList<Course>();
		try {
			courses = this.session.createQuery(Constant.DATABASE_QUERY.ALL_COURSES).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}

	public Course getCourse(int courseId) {
		Course course = null;
		try {
			course = (Course) this.session.get(Course.class, courseId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}

	public void saveOrUpdateCourse(Course course) {
		try {
			this.session.saveOrUpdate(course);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public boolean deleteCourse(int courseId) {
		try {
			Course course = (Course) this.session.get(Course.class, courseId);
			this.session.delete(course);
			this.session.flush();
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean hasDuplicates(int keyCourseId, int startYear, int endYear) {
		if (keyCourseId != 0)
			return false;
		Criteria criteria = this.session.createCriteria(Course.class);
		criteria.add(Restrictions.eq("startYear", startYear));
		criteria.add(Restrictions.eq("endYear", endYear));
		List<Course> results = criteria.list();
		if (results != null && results.size() > 0) {
			for (Course result : results) {
				// If another record with different ID then true
				if (result.getCourseId() != keyCourseId) {
					return true;
				}
			}
		}
		return false;
	}
}
