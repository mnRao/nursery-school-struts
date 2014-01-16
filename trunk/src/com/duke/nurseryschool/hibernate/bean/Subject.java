package com.duke.nurseryschool.hibernate.bean;

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

	@OneToMany(mappedBy = "subject")
	private SubjectFeeMap subjectFeeMap;

	public Subject() {
	}

	public Subject(int subjectId, String name, SubjectFeeMap subjectFeeMap) {
		super();
		this.subjectId = subjectId;
		this.name = name;
		this.subjectFeeMap = subjectFeeMap;
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

	public SubjectFeeMap getSubjectFeeMap() {
		return this.subjectFeeMap;
	}

	public void setSubjectFeeMap(SubjectFeeMap subjectFeeMap) {
		this.subjectFeeMap = subjectFeeMap;
	}

}
