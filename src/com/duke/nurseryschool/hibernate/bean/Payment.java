package com.duke.nurseryschool.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {
	@Column(name = "absenseCount")
	private int absenseCount;
	@Column(name = "totalNormalMealFee")
	private double totalNormalMealFee;
	@Column(name = "totalBreakfastFee")
	private double totalBreakfastFee;
	@Column(name = "totalFee")
	private double totalFee;
	@Column(name = "isPaid")
	private int isPaid;
	@Column(name = "note")
	private String note;

	@ManyToOne
	@JoinColumn(name = "studentId")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "feeDetailsId")
	private FeeDetails feeDetails;

	public Payment() {
	}

	public Payment(int absenseCount, double totalNormalMealFee,
			double totalBreakfastFee, double totalFee, int isPaid, String note,
			Student student, FeeDetails feeDetails) {
		super();
		this.absenseCount = absenseCount;
		this.totalNormalMealFee = totalNormalMealFee;
		this.totalBreakfastFee = totalBreakfastFee;
		this.totalFee = totalFee;
		this.isPaid = isPaid;
		this.note = note;
		this.student = student;
		this.feeDetails = feeDetails;
	}

	public int getAbsenseCount() {
		return this.absenseCount;
	}

	public void setAbsenseCount(int absenseCount) {
		this.absenseCount = absenseCount;
	}

	public double getTotalNormalMealFee() {
		return this.totalNormalMealFee;
	}

	public void setTotalNormalMealFee(double totalNormalMealFee) {
		this.totalNormalMealFee = totalNormalMealFee;
	}

	public double getTotalBreakfastFee() {
		return this.totalBreakfastFee;
	}

	public void setTotalBreakfastFee(double totalBreakfastFee) {
		this.totalBreakfastFee = totalBreakfastFee;
	}

	public double getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public int getIsPaid() {
		return this.isPaid;
	}

	public void setIsPaid(int isPaid) {
		this.isPaid = isPaid;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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
