package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.Student;
import com.duke.nurseryschool.hibernate.dao.ClassesDAO;
import com.duke.nurseryschool.hibernate.dao.StudentDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class StudentAction extends CoreAction implements ModelDriven<Student> {

	private static final long serialVersionUID = -3023527426370035860L;

	private Student student = new Student();
	private List<Student> students = new ArrayList<Student>();
	private final StudentDAO dao = new StudentDAO();
	private final ClassesDAO classesDAO = new ClassesDAO();

	private int classId;

	@Override
	public Student getModel() {
		return this.student;
	}

	// TODO Validation
	// @Override
	// public void validate() {
	// ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
	// Validator validator = vFactory.getValidator();
	// Set<ConstraintViolation<Student>> constraintViolations = validator
	// .validate(this.student);
	// for (ConstraintViolation<Student> violation : constraintViolations) {
	// this.addActionError(violation.getMessage());
	// }
	//
	// super.validate();
	// }

	public String saveOrUpdate() {
		// Set class based on ID
		Classes associatedClass = this.classesDAO.getClasses(this.classId);
		this.student.setAssociatedClass(associatedClass);

		this.dao.saveOrUpdateStudent(this.student);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.students = this.dao.getStudents();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteStudent(Integer.parseInt(this.request
				.getParameter("studentId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.student = this.dao.getStudent(Integer.parseInt(this.request
				.getParameter("studentId")));
		this.classId = this.student.getAssociatedClass().getClassId();

		// Load all
		this.students = this.dao.getStudents();
		return Action.SUCCESS;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public int getClassId() {
		return this.classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

}
