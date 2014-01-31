package com.duke.nurseryschool.hibernate.bean.embedded;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.duke.nurseryschool.hibernate.bean.FeeDetails;
import com.duke.nurseryschool.hibernate.bean.Student;

@Embeddable
public class StudentFeeDetails implements Serializable {
	@ManyToOne
	@JoinColumn(name = "studentId")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "feeDetailsId")
	private FeeDetails feeDetails;

	public StudentFeeDetails() {
	}

	public StudentFeeDetails(Student student, FeeDetails feeDetails) {
		this.student = student;
		this.feeDetails = feeDetails;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public FeeDetails getFeeDetails() {
		return this.feeDetails;
	}

	public void setFeeDetails(FeeDetails feeDetails) {
		this.feeDetails = feeDetails;
	}
}
