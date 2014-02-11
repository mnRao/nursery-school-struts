package com.duke.nurseryschool.hibernate.bean.embedded;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.Payment;

@Embeddable
public class PaymentFee implements Serializable {
	@ManyToOne
	@JoinColumn(name = "paymentId")
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "feeId")
	private Fee fee;

	public PaymentFee() {
	}

	public PaymentFee(Payment payment, Fee fee) {
		this.payment = payment;
		this.fee = fee;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Fee getFee() {
		return this.fee;
	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}

}
