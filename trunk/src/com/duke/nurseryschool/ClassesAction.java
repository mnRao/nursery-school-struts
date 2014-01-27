package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Course;
import com.duke.nurseryschool.hibernate.dao.ClassesDAO;
import com.duke.nurseryschool.hibernate.dao.CourseDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class ClassesAction extends CoreAction implements ModelDriven<Classes> {

	private Classes classes = new Classes();
	private List<Classes> allClasses = new ArrayList<Classes>();
	private ClassesDAO dao = new ClassesDAO();

	private CourseDAO courseDAO = new CourseDAO();
	private String courseId;
	private List<Course> courseList;
	private List<String> courseNameList;

	@Override
	public Classes getModel() {
		return this.classes;
	}

	public String saveOrUpdate() {
		// Save course based on ID
		Course course = this.courseDAO.getCourse(Integer
				.parseInt(this.courseId));
		this.classes.setCourse(course);

		this.dao.saveOrUpdateClasses(this.classes);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.populateCourseList();

		this.allClasses = this.dao.getClasses();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteClasses(Integer.parseInt(this.request
				.getParameter("classId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.populateCourseList();

		this.classes = this.dao.getClasses(Integer.parseInt(this.request
				.getParameter("classId")));
		// Load courseId
		this.courseId = Integer
				.toString(this.classes.getCourse().getCourseId());
		// Load all
		this.allClasses = this.dao.getClasses();
		return Action.SUCCESS;
	}

	private void populateCourseList() {
		// Populate class list
		this.courseList = this.courseDAO.getCourses();
		this.courseNameList = new ArrayList<>();
		for (Course course : this.courseList) {
			this.courseNameList.add(course.toString());
		}
	}

	public List<Classes> getAllClasses() {
		return this.allClasses;
	}

	public void setAllClasses(List<Classes> allClasses) {
		this.allClasses = allClasses;
	}

	public Classes getClasses() {
		return this.classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public List<Course> getCourseList() {
		return this.courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public List<String> getCourseNameList() {
		return this.courseNameList;
	}

	public void setCourseNameList(List<String> courseNameList) {
		this.courseNameList = courseNameList;
	}

}