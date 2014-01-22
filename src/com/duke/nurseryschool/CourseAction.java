package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Course;
import com.duke.nurseryschool.hibernate.dao.CourseDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class CourseAction extends CoreAction implements ModelDriven<Course> {

	private Course course = new Course();
	private List<Course> courses = new ArrayList<Course>();
	private CourseDAO dao = new CourseDAO();

	@Override
	public Course getModel() {
		return this.course;
	}

	public String saveOrUpdate() {
		this.dao.saveOrUpdateCourse(this.course);
		this.addActionMessage(this.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.courses = this.dao.getCourses();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteCourse(Integer.parseInt(this.request
				.getParameter("courseId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.course = this.dao.getCourse(Integer.parseInt(this.request
				.getParameter("courseId")));
		// Load all
		this.courses = this.dao.getCourses();
		return Action.SUCCESS;
	}

	public List<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
