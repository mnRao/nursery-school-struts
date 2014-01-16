package com.duke.nurseryschool.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "extra_fee_map")
public class ExtraFeeMap {
	@Column(name = "amount")
	private double amount;

	@ManyToOne
	@JoinColumn(name = "feeDetailsId")
	private FeeDetails feeDetails;

	@ManyToOne
	@JoinColumn(name = "extraFeeTypeId")
	private ExtraFeeType extraFeeType;

	public ExtraFeeMap() {
	}

	public ExtraFeeMap(double amount, FeeDetails feeDetails,
			ExtraFeeType extraFeeType) {
		super();
		this.amount = amount;
		this.feeDetails = feeDetails;
		this.extraFeeType = extraFeeType;
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

	public ExtraFeeType getExtraFeeType() {
		return this.extraFeeType;
	}

	public void setExtraFeeType(ExtraFeeType extraFeeType) {
		this.extraFeeType = extraFeeType;
	}

}
