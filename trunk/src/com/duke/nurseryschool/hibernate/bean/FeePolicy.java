package com.duke.nurseryschool.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fee_policy")
public class FeePolicy {
	@Column(name = "feePerNormalMeal")
	private double feePerNormalMeal;
	@Column(name = "feePerBreakfast")
	private double feePerBreakfast;
	@Column(name = "availableDays")
	private int availableDays;

	@ManyToOne
	@JoinColumn(name = "classId")
	private Classes associatedClass;

	@ManyToOne
	@JoinColumn(name = "monthId")
	private Month month;

	public FeePolicy() {
	}

	public FeePolicy(double feePerNormalMeal, double feePerBreakfast,
			int availableDays) {
		super();
		this.feePerNormalMeal = feePerNormalMeal;
		this.feePerBreakfast = feePerBreakfast;
		this.availableDays = availableDays;
	}

	public double getFeePerNormalMeal() {
		return this.feePerNormalMeal;
	}

	public void setFeePerNormalMeal(double feePerNormalMeal) {
		this.feePerNormalMeal = feePerNormalMeal;
	}

	public double getFeePerBreakfast() {
		return this.feePerBreakfast;
	}

	public void setFeePerBreakfast(double feePerBreakfast) {
		this.feePerBreakfast = feePerBreakfast;
	}

	public int getAvailableDays() {
		return this.availableDays;
	}

	public void setAvailableDays(int availableDays) {
		this.availableDays = availableDays;
	}

	public Classes getAssociatedClass() {
		return this.associatedClass;
	}

	public void setAssociatedClass(Classes associatedClass) {
		this.associatedClass = associatedClass;
	}

	public Month getMonth() {
		return this.month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

}
