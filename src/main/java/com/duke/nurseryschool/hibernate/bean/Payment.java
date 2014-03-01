package com.duke.nurseryschool.hibernate.bean;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.duke.nurseryschool.core.BeanLabel;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.PaymentTrigger;

@Entity
@Table(name = "payment")
public class Payment implements BeanLabel {
	@Id
	@GeneratedValue
	private int paymentId;
	@Column(name = "absenceCount")
	private int absenceCount;
	@Column(name = "hasBreakfast")
	private int hasBreakfast;
	@Column(name = "totalNormalMealFee", columnDefinition = "Decimal(10,1) default '0.0'")
	private BigDecimal totalNormalMealFee;
	@Column(name = "totalBreakfastFee", columnDefinition = "Decimal(10,1) default '0.0'")
	private BigDecimal totalBreakfastFee;
	@Column(name = "totalFee", columnDefinition = "Decimal(10,1) default '0.0'")
	private BigDecimal totalFee;
	@Column(name = "isPaid")
	private int isPaid;
	@Column(name = "note")
	private String note;

	@ManyToOne
	@JoinColumn(name = "feePolicyId")
	private FeePolicy feePolicy;
	@ManyToOne
	@JoinColumn(name = "studentId")
	private Student student;

	@OneToMany(mappedBy = "paymentFee.payment")
	private Set<AlternativeFeeMap> alternativeFeeMaps;

	public Payment() {
	}

	@Override
	public String getLabel() {
		StringBuilder label = new StringBuilder();
		if (this.getStudent() != null) {
			label.append(this.getStudent().getName());
		}
		if (this.getFeePolicy() != null) {
			label.append(Constant.PUNCTUATION_MARK.HYPHEN).append(
					this.getFeePolicy().getMonth().getLabel());
		}

		return label.toString();
	}

	public int getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getAbsenceCount() {
		return this.absenceCount;
	}

	public void setAbsenceCount(int absenceCount) {
		this.absenceCount = absenceCount;
	}

	public int getHasBreakfast() {
		return this.hasBreakfast;
	}

	public void setHasBreakfast(int hasBreakfast) {
		this.hasBreakfast = hasBreakfast;
	}

	public BigDecimal getTotalNormalMealFee() {
		return this.totalNormalMealFee;
	}

	public void setTotalNormalMealFee(BigDecimal totalNormalMealFee) {
		this.totalNormalMealFee = totalNormalMealFee;
	}

	public BigDecimal getTotalBreakfastFee() {
		return this.totalBreakfastFee;
	}

	public void setTotalBreakfastFee(BigDecimal totalBreakfastFee) {
		this.totalBreakfastFee = totalBreakfastFee;
	}

	public BigDecimal getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public int getIsPaid() {
		return this.isPaid;
	}

	public void setIsPaid(int isPaid) {
		this.isPaid = isPaid;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public FeePolicy getFeePolicy() {
		return this.feePolicy;
	}

	public void setFeePolicy(FeePolicy feePolicy) {
		this.feePolicy = feePolicy;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Set<AlternativeFeeMap> getAlternativeFeeMaps() {
		return this.alternativeFeeMaps;
	}

	public void setAlternativeFeeMaps(Set<AlternativeFeeMap> alternativeFeeMaps) {
		this.alternativeFeeMaps = alternativeFeeMaps;
	}

}