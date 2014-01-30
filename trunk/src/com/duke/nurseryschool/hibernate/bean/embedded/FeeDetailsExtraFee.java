package com.duke.nurseryschool.hibernate.bean.embedded;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.duke.nurseryschool.hibernate.bean.ExtraFeeType;
import com.duke.nurseryschool.hibernate.bean.FeeDetails;

@Embeddable
public class FeeDetailsExtraFee implements Serializable {
	@ManyToOne
	@JoinColumn(name = "feeDetailsId")
	private FeeDetails feeDetails;

	@ManyToOne
	@JoinColumn(name = "extraFeeTypeId")
	private ExtraFeeType extraFeeType;

	public FeeDetailsExtraFee() {
	}

	public FeeDetailsExtraFee(FeeDetails feeDetails, ExtraFeeType extraFeeType) {
		this.feeDetails = feeDetails;
		this.extraFeeType = extraFeeType;
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
