package com.duke.nurseryschool.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.duke.nurseryschool.hibernate.bean.embedded.ClassMonth;

@Entity
@Table(name = "fee_policy")
public class FeePolicy {
	@Column(name = "feePerNormalMeal")
	private double feePerNormalMeal;
	@Column(name = "feePerBreakfast")
	private double feePerBreakfast;
	@Column(name = "availableDays")
	private int availableDays;

	@EmbeddedId
	private ClassMonth classMonth;

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

}
