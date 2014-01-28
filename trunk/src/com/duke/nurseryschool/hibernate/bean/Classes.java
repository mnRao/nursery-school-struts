package com.duke.nurseryschool.hibernate.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.duke.nurseryschool.helper.Grade;
import com.duke.nurseryschool.helper.Helper;

@Entity
@Table(name = "class")
public class Classes {
	@Id
	@GeneratedValue
	private int classId;
	@Column(name = "code")
	private String code;
	@ManyToOne
	@JoinColumn(name = "courseId")
	private Course course;

	@OneToMany(mappedBy = "associatedClass")
	private Set<Student> students;

	@OneToMany(mappedBy = "classMonth.associatedClass")
	private Set<FeePolicy> feePolicies;

	// Virtual current grade
	private Grade grade;

	public Classes() {

	}

	public Classes(String code) {
		this.code = code;
	}

	public String getCurrentName() {
		int startYear = this.course.getStartYear();
		int endYear = this.course.getEndYear();
		int currentYear = Helper.calculateCurrentYear();
		if (currentYear >= endYear) {
			this.grade = Helper.getHighestGrade();
		}
		else if (currentYear <= startYear) {
			this.grade = Helper.getLowestGrade();
		}
		else if (currentYear == startYear + 1) {
			this.grade = Grade.THIRD;
		}
		else {
			this.grade = Grade.FOURTH;
		}
		// TODO Use month to calculate grade

		return this.grade.getOfficialLabel();
	}

	public int getClassId() {
		return this.classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<FeePolicy> getFeePolicies() {
		return this.feePolicies;
	}

	public void setFeePolicies(Set<FeePolicy> feePolicies) {
		this.feePolicies = feePolicies;
	}

}
