package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.Student;
import com.duke.nurseryschool.hibernate.StudentDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class StudentAction extends ActionSupport implements
		ModelDriven<Student> {

	Student student = new Student();
	List<Student> students = new ArrayList<Student>();
	StudentDAO dao = new StudentDAO();

	@Override
	public Student getModel() {
		return student;
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
