package com.duke.nurseryschool.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.duke.nurseryschool.hibernate.bean.embedded.FeeDetailsExtraFee;

@Entity
@Table(name = "extra_fee_map")
public class ExtraFeeMap {
	@Column(name = "amount")
	private double amount;

	@EmbeddedId
	private FeeDetailsExtraFee feeDetailsExtraFee;

	public ExtraFeeMap() {
	}

	public ExtraFeeMap(double amount, FeeDetails feeDetails,
			ExtraFeeType extraFeeType) {
		this.amount = amount;
		this.feeDetailsExtraFee = new FeeDetailsExtraFee(feeDetails,
				extraFeeType);
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public FeeDetailsExtraFee getFeeDetailsExtraFee() {
		return this.feeDetailsExtraFee;
	}

	public void setFeeDetailsExtraFee(FeeDetailsExtraFee feeDetailsExtraFee) {
		this.feeDetailsExtraFee = feeDetailsExtraFee;
	}

}
