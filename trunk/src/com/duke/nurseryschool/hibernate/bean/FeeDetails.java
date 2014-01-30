package com.duke.nurseryschool.hibernate.bean;

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

@Entity
@Table(name = "fee_details")
public class FeeDetails implements BeanLabel {
	@Id
	@GeneratedValue
	private int feeDetailsId;
	@Column(name = "basicStudyFee")
	private double basicStudyFee;
	@Column(name = "totalExtraFee")
	private double totalExtraFee;
	@Column(name = "totalExtraStudyFee")
	private double totalExtraStudyFee;

	@ManyToOne
	@JoinColumn(name = "classId")
	private Classes associatedClass;
	@ManyToOne
	@JoinColumn(name = "monthId")
	private Month month;

	@OneToMany(mappedBy = "studentFeeDetails.feeDetails")
	private Set<Payment> payments;

	@OneToMany(mappedBy = "feeDetailsExtraFee.feeDetails")
	private Set<ExtraFeeMap> extraFeeMaps;

	@OneToMany(mappedBy = "subjectFee.feeDetails")
	private Set<SubjectFeeMap> subjectFeeMaps;

	public FeeDetails() {
	}

	public FeeDetails(double basicStudyFee, double totalExtraFee,
			double totalExtraStudyFee) {
		this.basicStudyFee = basicStudyFee;
		this.totalExtraFee = totalExtraFee;
		this.totalExtraStudyFee = totalExtraStudyFee;
	}

	@Override
	public String getLabel() {
		return this.associatedClass.getLabel()
				+ Constant.PUNCTUATION_MARK.HYPHEN + this.month.getLabel();
	}

	public int getFeeDetailsId() {
		return this.feeDetailsId;
	}

	public void setFeeDetailsId(int feeDetailsId) {
		this.feeDetailsId = feeDetailsId;
	}

	public double getBasicStudyFee() {
		return this.basicStudyFee;
	}

	public void setBasicStudyFee(double basicStudyFee) {
		this.basicStudyFee = basicStudyFee;
	}

	public double getTotalExtraFee() {
		return this.totalExtraFee;
	}

	public void setTotalExtraFee(double totalExtraFee) {
		this.totalExtraFee = totalExtraFee;
	}

	public double getTotalExtraStudyFee() {
		return this.totalExtraStudyFee;
	}

	public void setTotalExtraStudyFee(double totalExtraStudyFee) {
		this.totalExtraStudyFee = totalExtraStudyFee;
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

	public Set<ExtraFeeMap> getExtraFeeMaps() {
		return this.extraFeeMaps;
	}

	public void setExtraFeeMaps(Set<ExtraFeeMap> extraFeeMaps) {
		this.extraFeeMaps = extraFeeMaps;
	}

	public Set<SubjectFeeMap> getSubjectFeeMaps() {
		return this.subjectFeeMaps;
	}

	public void setSubjectFeeMaps(Set<SubjectFeeMap> subjectFeeMaps) {
		this.subjectFeeMaps = subjectFeeMaps;
	}

}
