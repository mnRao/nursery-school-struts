package com.duke.nurseryschool.hibernate.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue
	private int studentId;
	@Column(name = "name")
	private String name;
	@Column(name = "dateOfBirth")
	private Date dateOfBirth;
	@Column(name = "gender")
	private int gender;
	@Column(name = "address")
	private String address;
	@Column(name = "homePhone")
	private String homePhone;
	@Column(name = "isActive")
	private boolean isActive;
	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "classId")
	private Classes associatedClass;

	@ManyToMany(cascade = {
		CascadeType.ALL
	})
	@JoinTable(name = "parent_student_map", joinColumns = {
		@JoinColumn(name = "studentId")
	}, inverseJoinColumns = {
		@JoinColumn(name = "parentId")
	})
	private Set<Parent> parents = new HashSet<Parent>();

	public Student() {
	}

	public Student(String name, Date dateOfBirth, int gender, String address,
			String homePhone, boolean isActive, String description) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		this.homePhone = homePhone;
		this.isActive = isActive;
		this.description = description;
	}

	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public boolean isActive() {
		return this.isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Classes getAssociatedClass() {
		return this.associatedClass;
	}

	public void setAssociatedClass(Classes associatedClass) {
		this.associatedClass = associatedClass;
	}

	public Set<Parent> getParents() {
		return this.parents;
	}

	public void setParents(Set<Parent> parents) {
		this.parents = parents;
	}

}
