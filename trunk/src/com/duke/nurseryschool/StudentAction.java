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
import com.duke.nurseryschool.hibernate.bean.Student;
import com.duke.nurseryschool.hibernate.dao.StudentDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class StudentAction extends CoreAction implements ModelDriven<Student> {

	private static final long serialVersionUID = -3023527426370035860L;

	private Student student = new Student();
	private List<Student> students = new ArrayList<Student>();
	private final StudentDAO dao = new StudentDAO();

	@Override
	public Student getModel() {
		return student;
	}

	@Override
	public void validate() {
		ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = vFactory.getValidator();
		Set<ConstraintViolation<Student>> constraintViolations = validator
				.validate(student);
		for (ConstraintViolation<Student> violation : constraintViolations) {
			addActionError(violation.getMessage());
		}

		super.validate();
	}

	@Override
	public String execute() {
		dao.addStudent(student);
		return Action.SUCCESS;
	}

	public String listStudents() {
		students = dao.getStudents();
		return Action.SUCCESS;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
