package com.duke.nurseryschool.hibernate.bean;

import java.math.BigDecimal;
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
	@Column(name = "feePerNormalMeal", columnDefinition = "Decimal(10,1) default '0.0'")
	private BigDecimal feePerNormalMeal;
	@Column(name = "penaltyFeePerBreakfast", columnDefinition = "Decimal(10,1) default '0.0'")
	private BigDecimal penaltyFeePerBreakfast;
	@Column(name = "totalBreakfastFee", columnDefinition = "Decimal(10,1) default '0.0'")
	private BigDecimal totalBreakfastFee;
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
		StringBuilder label = new StringBuilder();
		if (this.associatedClass != null) {
			label.append(this.associatedClass.getLabel());
		}
		if (this.month != null) {
			label.append(Constant.PUNCTUATION_MARK.HYPHEN).append(
					this.month.getLabel());
		}
		return label.toString();
	}

	public int getFeePolicyId() {
		return this.feePolicyId;
	}

	public void setFeePolicyId(int feePolicyId) {
		this.feePolicyId = feePolicyId;
	}

	public BigDecimal getFeePerNormalMeal() {
		return this.feePerNormalMeal;
	}

	public void setFeePerNormalMeal(BigDecimal feePerNormalMeal) {
		this.feePerNormalMeal = feePerNormalMeal;
	}

	public BigDecimal getPenaltyFeePerBreakfast() {
		return this.penaltyFeePerBreakfast;
	}

	public void setPenaltyFeePerBreakfast(BigDecimal penaltyFeePerBreakfast) {
		this.penaltyFeePerBreakfast = penaltyFeePerBreakfast;
	}

	public BigDecimal getTotalBreakfastFee() {
		return this.totalBreakfastFee;
	}

	public void setTotalBreakfastFee(BigDecimal totalBreakfastFee) {
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
