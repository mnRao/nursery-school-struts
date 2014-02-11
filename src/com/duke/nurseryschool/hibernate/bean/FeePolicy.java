package com.duke.nurseryschool.hibernate.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.duke.nurseryschool.core.BeanLabel;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.embedded.ClassMonth;

@Entity
@Table(name = "fee_policy")
public class FeePolicy implements BeanLabel {
	@Id
	@GeneratedValue
	private int feePolicyId;
	@Column(name = "feePerNormalMeal")
	private double feePerNormalMeal;
	@Column(name = "penaltyFeePerBreakfast")
	private double penaltyFeePerBreakfast;
	@Column(name = "totalBreakfastFee")
	private double totalBreakfastFee;
	@Column(name = "availableDays")
	private int availableDays;

	@ManyToOne
	@JoinColumn(name = "classId")
	private Classes associatedClass;
	@ManyToOne
	@JoinColumn(name = "monthId")
	private Month month;

	@OneToMany(mappedBy = "feePolicy")
	private Set<Payment> payments;

	@OneToMany(mappedBy = "feePolicyFee.feePolicy")
	private Set<FeeMap> feeMaps;

	public FeePolicy() {

	}

	@Override
	public String getLabel() {
		return this.associatedClass.getLabel()
				+ Constant.PUNCTUATION_MARK.HYPHEN + this.month.getLabel();
	}

	public int getFeePolicyId() {
		return this.feePolicyId;
	}

	public void setFeePolicyId(int feePolicyId) {
		this.feePolicyId = feePolicyId;
	}

	public double getFeePerNormalMeal() {
		return this.feePerNormalMeal;
	}

	public void setFeePerNormalMeal(double feePerNormalMeal) {
		this.feePerNormalMeal = feePerNormalMeal;
	}

	public double getPenaltyFeePerBreakfast() {
		return this.penaltyFeePerBreakfast;
	}

	public void setPenaltyFeePerBreakfast(double penaltyFeePerBreakfast) {
		this.penaltyFeePerBreakfast = penaltyFeePerBreakfast;
	}

	public double getTotalBreakfastFee() {
		return this.totalBreakfastFee;
	}

	public void setTotalBreakfastFee(double totalBreakfastFee) {
		this.totalBreakfastFee = totalBreakfastFee;
	}

	public int getAvailableDays() {
		return this.availableDays;
	}

	public void setAvailableDays(int availableDays) {
		this.availableDays = availableDays;
	}

	public Classes getAssociatedClass() {
		return this.associatedClass;
	}

	public void setAssociatedClass(Classes associatedClass) {
		this.associatedClass = associatedClass;
	}

	public Month getMonth() {
		return this.month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public Set<FeeMap> getFeeMaps() {
		return this.feeMaps;
	}

	public void setFeeMaps(Set<FeeMap> feeMaps) {
		this.feeMaps = feeMaps;
	}

}
