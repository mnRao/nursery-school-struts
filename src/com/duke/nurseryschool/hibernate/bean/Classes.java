package com.duke.nurseryschool.hibernate.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.duke.nurseryschool.core.BeanLabel;
import com.duke.nurseryschool.helper.BusinessLogicSolver;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.Grade;

@Entity
@Table(name = "class")
public class Classes implements BeanLabel {
	@Id
	@GeneratedValue
	private int classId;
	@NotEmpty
	@Length(min = 2, max = 20)
	@Column(name = "code")
	private String code;
	@ManyToOne
	@JoinColumn(name = "courseId")
	private Course course;

	@OneToMany(mappedBy = "associatedClass")
	private Set<Student> students;

	@OneToMany(mappedBy = "associatedClass")
	private Set<FeePolicy> feePolicies;

	// Virtual current grade
	private Grade grade;

	public Classes() {

	}

	public Classes(String code) {
		this.code = code;
	}

	@Override
	public String getLabel() {
		StringBuilder label = new StringBuilder();
		label.append(Constant.PUNCTUATION_MARK.BRACKET_CURLY_OPEN);
		if (this.course != null) {
			label.append(this.course.getLabel()).append(
					Constant.PUNCTUATION_MARK.HYPHEN);
		}
		label.append(Constant.PUNCTUATION_MARK.PARENTHESIS_OPEN);
		if (this.grade != null) {
			label.append(this.grade.getOfficialLabel());
		}
		else {
			label.append(this.getCurrentName());
		}
		label.append(Constant.PUNCTUATION_MARK.PARENTHESIS_CLOSE);

		label.append(Constant.PUNCTUATION_MARK.HYPHEN);
		label.append(Constant.PUNCTUATION_MARK.PARENTHESIS_OPEN);
		label.append(this.code);
		label.append(Constant.PUNCTUATION_MARK.PARENTHESIS_CLOSE);
		label.append(Constant.PUNCTUATION_MARK.BRACKET_CURLY_CLOSE);

		return label.toString();
	}

	public String getCurrentName() {
		if (this.getCourse() != null) {
			this.grade = BusinessLogicSolver.calculateGrade(this.getCourse()
					.getStartYear(), this.getCourse().getEndYear());
		}
		else {
			this.grade = Grade.UNIDENTIFIED;
		}

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

	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

}
