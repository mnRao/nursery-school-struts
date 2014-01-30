package com.duke.nurseryschool.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.duke.nurseryschool.core.BeanLabel;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.embedded.ClassMonth;

@Entity
@Table(name = "fee_policy")
public class FeePolicy implements BeanLabel {
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
		this.feePerNormalMeal = feePerNormalMeal;
		this.feePerBreakfast = feePerBreakfast;
		this.availableDays = availableDays;
	}

	@Override
	public String getLabel() {
		return this.classMonth.getAssociatedClass().getLabel()
				+ Constant.PUNCTUATION_MARK.HYPHEN
				+ this.classMonth.getMonth().getLabel();
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

	public ClassMonth getClassMonth() {
		return this.classMonth;
	}

	public void setClassMonth(ClassMonth classMonth) {
		this.classMonth = classMonth;
	}

}
