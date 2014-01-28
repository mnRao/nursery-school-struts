package com.duke.nurseryschool.hibernate.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.duke.nurseryschool.helper.Constant;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue
	private int courseId;
	@Column(name = "startYear")
	private int startYear;
	@Column(name = "endYear")
	private int endYear;
	@OneToMany(mappedBy = "course")
	private Set<Classes> classes;

	public Course() {

	}

	public Course(int startYear, int endYear) {
		this.startYear = startYear;
		this.endYear = endYear;
	}

	@Override
	public String toString() {
		return Constant.PUNCTUATION_MARK.BRACKET_OPEN + this.startYear
				+ Constant.PUNCTUATION_MARK.HYPHEN + this.endYear
				+ Constant.PUNCTUATION_MARK.BRACKET_CLOSE;
	}

	public String getLabel() {
		return this.toString();
	}

	public int getCourseId() {
		return this.courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getStartYear() {
		return this.startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return this.endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public Set<Classes> getClasses() {
		return this.classes;
	}

	public void setClasses(Set<Classes> classes) {
		this.classes = classes;
	}

}