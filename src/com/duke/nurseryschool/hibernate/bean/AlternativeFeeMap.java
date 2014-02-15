package com.duke.nurseryschool.hibernate.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.duke.nurseryschool.hibernate.bean.embedded.PaymentFee;

@Entity
@Table(name = "alternative_fee_map")
public class AlternativeFeeMap {

	@Column(name = "alternativeAmount", columnDefinition = "Decimal(10,1) default '0.0'")
	private BigDecimal alternativeAmount;

	@EmbeddedId
	private PaymentFee paymentFee;

	public AlternativeFeeMap() {
	}

	public BigDecimal getAlternativeAmount() {
		return this.alternativeAmount;
	}

	public void setAlternativeAmount(BigDecimal alternativeAmount) {
		this.alternativeAmount = alternativeAmount;
	}

	public PaymentFee getPaymentFee() {
		return this.paymentFee;
	}

	public void setPaymentFee(PaymentFee paymentFee) {
		this.paymentFee = paymentFee;
	}

}
