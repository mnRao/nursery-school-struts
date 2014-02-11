package com.duke.nurseryschool.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.duke.nurseryschool.hibernate.bean.embedded.FeePolicyFee;

@Entity
@Table(name = "fee_map")
public class FeeMap {
	@Column(name = "amount")
	private double amount;

	@EmbeddedId
	private FeePolicyFee feePolicyFee;

	public FeeMap() {
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public FeePolicyFee getFeePolicyFee() {
		return this.feePolicyFee;
	}

	public void setFeePolicyFee(FeePolicyFee feePolicyFee) {
		this.feePolicyFee = feePolicyFee;
	}

}
