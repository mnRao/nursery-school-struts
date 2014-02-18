package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Course;
import com.duke.nurseryschool.hibernate.dao.CourseDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class CourseAction extends CoreAction implements ModelDriven<Course>,
		Preparable {

	// @Valid
	private Course course = new Course();
	private List<Course> courses = new ArrayList<Course>();
	private CourseDAO dao = new CourseDAO();

	@Override
	public Course getModel() {
		return this.course;
	}

	public String saveOrUpdate() {
		this.dao.getSession().evict(
				this.dao.getCourse(Integer.parseInt(this.request
						.getParameter("courseId"))));

		this.dao.saveOrUpdateCourse(this.course);

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String list() {
		return Action.SUCCESS;
	}

	@SkipValidation
	public String delete() {
		this.dao.deleteCourse(Integer.parseInt(this.request
				.getParameter("courseId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String edit() {
		this.course = this.dao.getCourse(Integer.parseInt(this.request
				.getParameter("courseId")));
		// Load all
		this.courses = this.dao.getCourses();
		return Action.SUCCESS;
	}

	@Override
	public void validate() {
		int startYear = this.course.getStartYear();
		int endYear = this.course.getEndYear();
		if (startYear <= 0) {
			this.addFieldError("course.startYear", this
					.getText(Constant.I18N.ERROR_CONSTRAINT_COURSE_STARTYEAR));
		}
		if (endYear <= 0) {
			this.addFieldError("course.endYear",
					this.getText(Constant.I18N.ERROR_CONSTRAINT_COURSE_ENDYEAR));
		}
		if (startYear != endYear - 4) {
			this.addFieldError("course.startYear",
					this.getText(Constant.I18N.ERROR_CONSTRAINT_COURSE_YEARS));
		}

		super.validate();
	}

	@Override
	public void prepare() throws Exception {
	}

	public void prepareList() throws Exception {
		this.populateData();
	}

	public void prepareEdit() throws Exception {
		this.populateData();
	}

	public void prepareSaveOrUpdate() throws Exception {
		this.populateData();
	}

	private void populateData() {
		this.courses = this.dao.getCourses();
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
