package com.duke.nurseryschool.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subject_fee_map")
public class SubjectFeeMap {

	@Column(name = "amount")
	private double amount;

	@ManyToOne
	@JoinColumn(name = "feeDetailsId")
	private FeeDetails feeDetails;

	@ManyToOne
	@JoinColumn(name = "subjectId")
	private Subject subject;

	public SubjectFeeMap() {
	}

	public SubjectFeeMap(double amount, FeeDetails feeDetails, Subject subject) {
		super();
		this.amount = amount;
		this.feeDetails = feeDetails;
		this.subject = subject;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public FeeDetails getFeeDetails() {
		return this.feeDetails;
	}

	public void setFeeDetails(FeeDetails feeDetails) {
		this.feeDetails = feeDetails;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}
