package com.duke.nurseryschool.hibernate.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.duke.nurseryschool.core.BeanLabel;
import com.duke.nurseryschool.helper.BusinessLogicSolver;
import com.duke.nurseryschool.helper.Constant;

@Entity
@Table(name = "parent")
public class Parent implements BeanLabel {
	@Id
	@GeneratedValue
	private int parentId;
	@Column(name = "gender")
	private int gender;
	@Column(name = "name")
	private String name;
	@Column(name = "job")
	private String job;
	@Column(name = "phoneNumber")
	private String phoneNumber;

	@ManyToMany(mappedBy = "parents")
	private Set<Student> students = new HashSet<Student>();

	public Parent() {
	}

	public Parent(int gender, String name, String job, String phoneNumber) {
		this.gender = gender;
		this.name = name;
		this.job = job;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String getLabel() {
		return Constant.PUNCTUATION_MARK.BRACKET_SQUARE_OPEN + this.name
				+ Constant.PUNCTUATION_MARK.HYPHEN + this.job
				+ Constant.PUNCTUATION_MARK.BRACKET_SQUARE_CLOSE;
	}

	@Override
	public String getTooltip() {
		return this.getLabel();
	}

	public String getGenderText() throws InstantiationException,
			IllegalAccessException {
		return BusinessLogicSolver.calculateGenderText(this.gender);
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

}
