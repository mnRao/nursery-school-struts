package com.duke.nurseryschool.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.duke.nurseryschool.hibernate.bean.embedded.SubjectFee;

@Entity
@Table(name = "subject_fee_map")
public class SubjectFeeMap {

	@Column(name = "amount")
	private double amount;

	@EmbeddedId
	private SubjectFee subjectFee;

	public SubjectFeeMap() {
	}

	public SubjectFeeMap(double amount, FeeDetails feeDetails, Subject subject) {
		this.amount = amount;
		this.subjectFee = new SubjectFee(feeDetails, subject);
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public SubjectFee getSubjectFee() {
		return this.subjectFee;
	}

	public void setSubjectFee(SubjectFee subjectFee) {
		this.subjectFee = subjectFee;
	}

}
