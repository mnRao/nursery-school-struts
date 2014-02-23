package com.duke.nurseryschool.hibernate.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.duke.nurseryschool.core.BeanLabel;
import com.duke.nurseryschool.helper.Constant;

@Entity
@Table(name = "course")
public class Course implements BeanLabel {
	@Id
	@GeneratedValue
	private int courseId;
	// @NotNull
	@Column(name = "startYear")
	private int startYear;
	// @NotNull(message = "Could not be null")
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
	public String getLabel() {
		return Constant.PUNCTUATION_MARK.BRACKET_SQUARE_OPEN + this.startYear
				+ Constant.PUNCTUATION_MARK.HYPHEN + this.endYear
				+ Constant.PUNCTUATION_MARK.BRACKET_SQUARE_CLOSE;
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
