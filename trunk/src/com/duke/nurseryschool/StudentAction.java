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
import com.duke.nurseryschool.hibernate.Student;
import com.duke.nurseryschool.hibernate.StudentDAO;
import com.opensymphony.xwork2.ModelDriven;

public class StudentAction extends CoreAction implements ModelDriven<Student> {

	Student student = new Student();
	List<Student> students = new ArrayList<Student>();
	StudentDAO dao = new StudentDAO();

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
		return Constant.RESPONSE_SUCCESS;
	}

	public String listStudents() {
		students = dao.getStudents();
		return Constant.RESPONSE_SUCCESS;
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
