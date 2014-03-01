package com.duke.nurseryschool.hibernate.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.duke.nurseryschool.hibernate.bean.embedded.FeePolicyFee;

@Entity
@Table(name = "fee_map")
public class FeeMap {
	@Column(name = "amount", columnDefinition = "Decimal(10,1) default '0.0'")
	private BigDecimal amount;

	@EmbeddedId
	private FeePolicyFee feePolicyFee;

	public FeeMap() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public FeePolicyFee getFeePolicyFee() {
		return this.feePolicyFee;
	}

	public void setFeePolicyFee(FeePolicyFee feePolicyFee) {
		this.feePolicyFee = feePolicyFee;
	}

}
