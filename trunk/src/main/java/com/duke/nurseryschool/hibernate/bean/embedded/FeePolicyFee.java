package com.duke.nurseryschool.hibernate.bean.embedded;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;

@Embeddable
public class FeePolicyFee implements Serializable {

	private static final long serialVersionUID = 3871262136219929998L;

	@ManyToOne
	@JoinColumn(name = "feePolicyId")
	private FeePolicy feePolicy;

	@ManyToOne
	@JoinColumn(name = "feeId")
	private Fee fee;

	public FeePolicyFee() {
	}

	public FeePolicyFee(FeePolicy feePolicy, Fee fee) {
		super();
		this.feePolicy = feePolicy;
		this.fee = fee;
	}

	public FeePolicy getFeePolicy() {
		return this.feePolicy;
	}

	public void setFeePolicy(FeePolicy feePolicy) {
		this.feePolicy = feePolicy;
	}

	public Fee getFee() {
		return this.fee;
	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}

}
