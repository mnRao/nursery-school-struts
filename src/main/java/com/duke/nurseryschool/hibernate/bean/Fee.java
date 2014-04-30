package com.duke.nurseryschool.hibernate.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.duke.nurseryschool.core.BeanLabel;
import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.FeeType;
import com.duke.nurseryschool.helper.Helper;

@Entity
@Table(name = "fee")
public class Fee implements BeanLabel {
	@Id
	@GeneratedValue
	private int feeId;
	@Column(name = "name")
	private String name;
	@Column(name = "type")
	private FeeType type;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "feePolicyFee.fee")
	private Set<FeeMap> feeMaps;

	@OneToMany(mappedBy = "paymentFee.fee")
	private Set<AlternativeFeeMap> alternativeFeeMaps;

	@ManyToOne
	@JoinColumn(name = "feeGroupId")
	private FeeGroup feeGroup;

	public Fee() {
	}

	public Fee(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getLabel() {
		String label = (this.feeGroup == null) ? Constant.EMPTY_STRING
				: this.feeGroup.getLabel() + Constant.PUNCTUATION_MARK.HYPHEN;
		label += this.name;

		return label;
	}

	@Override
	public String getTooltip() {
		return Helper.getI18N(I18N.TOOLTIP_FEE, new String[] {
				this.feeGroup.getName(), this.name
		});
	}

	public String getFeeIdAsString() {
		return Integer.toString(this.feeId);
	}

	public int getFeeId() {
		return this.feeId;
	}

	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FeeType getType() {
		return this.type;
	}

	public void setType(FeeType type) {
		this.type = type;
	}

	public Set<FeeMap> getFeeMaps() {
		return this.feeMaps;
	}

	public void setFeeMaps(Set<FeeMap> feeMaps) {
		this.feeMaps = feeMaps;
	}

	public Set<AlternativeFeeMap> getAlternativeFeeMaps() {
		return this.alternativeFeeMaps;
	}

	public void setAlternativeFeeMaps(Set<AlternativeFeeMap> alternativeFeeMaps) {
		this.alternativeFeeMaps = alternativeFeeMaps;
	}

	public FeeGroup getFeeGroup() {
		return this.feeGroup;
	}

	public void setFeeGroup(FeeGroup feeGroup) {
		this.feeGroup = feeGroup;
	}

}
