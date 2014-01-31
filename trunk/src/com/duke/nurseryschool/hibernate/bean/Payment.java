package com.duke.nurseryschool.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.duke.nurseryschool.hibernate.bean.embedded.StudentFeeDetails;

@Entity
@Table(name = "payment")
public class Payment {
	@Column(name = "absenceCount")
	private int absenceCount;
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

	@EmbeddedId
	private StudentFeeDetails studentFeeDetails;

	public Payment() {
	}

	public Payment(int absenseCount, double totalNormalMealFee,
			double totalBreakfastFee, double totalFee, int isPaid, String note,
			Student student, FeeDetails feeDetails) {
		this.absenceCount = absenseCount;
		this.totalNormalMealFee = totalNormalMealFee;
		this.totalBreakfastFee = totalBreakfastFee;
		this.totalFee = totalFee;
		this.isPaid = isPaid;
		this.note = note;
		this.studentFeeDetails = new StudentFeeDetails(student, feeDetails);
	}

	public int getAbsenceCount() {
		return this.absenceCount;
	}

	public void setAbsenceCount(int absenceCount) {
		this.absenceCount = absenceCount;
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

	public StudentFeeDetails getStudentFeeDetails() {
		return this.studentFeeDetails;
	}

	public void setStudentFeeDetails(StudentFeeDetails studentFeeDetails) {
		this.studentFeeDetails = studentFeeDetails;
	}

}
