package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.StringUtil;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Course;
import com.duke.nurseryschool.hibernate.dao.ClassesDAO;
import com.duke.nurseryschool.hibernate.dao.CourseDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ClassesAction extends CoreAction implements ModelDriven<Classes>,
		Preparable {

	private Classes			classes		= new Classes();
	private List<Classes>	allClasses	= new ArrayList<Classes>();
	private ClassesDAO		dao			= new ClassesDAO();

	private CourseDAO		courseDAO	= new CourseDAO();
	private int				courseId;
	private List<Course>	courseList;

	@Override
	public Classes getModel() {
		return this.classes;
	}

	public String saveOrUpdate() {
		// 1st solution: this.dao.getSession().clear();
		// => clear all in session
		// 2nd solution: evict() the previous one in session
		this.dao.getSession().evict(
				this.dao.getClasses(Integer.parseInt(this.request
						.getParameter("classId"))));
		// Save course based on ID
		Course course = this.courseDAO
				.getCourse(Integer.valueOf(this.courseId));
		this.classes.setCourse(course);

		this.dao.saveOrUpdateClasses(this.classes);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// return this.list();
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String list() {
		return Action.SUCCESS;
	}

	public String autoSetCourse() {
		return this.list();
	}

	@SkipValidation
	public String delete() {
		this.dao.deleteClasses(Integer.parseInt(this.request
				.getParameter("classId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String edit() {
		this.classes = this.dao.getClasses(Integer.parseInt(this.request
				.getParameter("classId")));
		// Load courseId
		this.courseId = this.classes.getCourse().getCourseId();

		return Action.SUCCESS;
	}

	@Override
	public void validate() {
		if (this.courseId <= 0) {
			this.addFieldError("courseId", "Course Id null");
		}
		if (StringUtil.isEmpty(this.classes.getCode())) {
			this.addFieldError("classes.code", "Class code null");
		}

		super.validate();
	}

	@Override
	public void prepare() throws Exception {
		// this.populateData();
	}

	public void prepareList() throws Exception {
		this.populateData();
	}

	public void prepareEdit() throws Exception {
		this.populateData();
	}

	public void prepareSaveOrUpdate() throws Exception {
		this.populateData();
		// this.validate();
	}

	private void populateData() {
		this.allClasses = this.dao.getClasses();
		// Populate class list
		this.courseList = this.courseDAO.getCourses();
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

	public int getCourseId() {
		return this.courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public List<Course> getCourseList() {
		return this.courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

}