package com.duke.nurseryschool.hibernate.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {
	@Id
	@GeneratedValue
	private int subjectId;
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "subjectFee.subject")
	private Set<SubjectFeeMap> subjectFeeMaps;

	public Subject() {
	}

	public Subject(String name) {
		this.name = name;
	}

	public int getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SubjectFeeMap> getSubjectFeeMap() {
		return this.subjectFeeMaps;
	}

	public void setSubjectFeeMap(Set<SubjectFeeMap> subjectFeeMaps) {
		this.subjectFeeMaps = subjectFeeMaps;
	}

	public Set<SubjectFeeMap> getSubjectFeeMaps() {
		return this.subjectFeeMaps;
	}

	public void setSubjectFeeMaps(Set<SubjectFeeMap> subjectFeeMaps) {
		this.subjectFeeMaps = subjectFeeMaps;
	}

}
