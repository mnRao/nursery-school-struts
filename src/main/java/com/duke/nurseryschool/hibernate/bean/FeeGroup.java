package com.duke.nurseryschool.hibernate.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.duke.nurseryschool.core.BeanLabel;
import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.BusinessLogicSolver;
import com.duke.nurseryschool.helper.Helper;

@Entity
@Table(name = "fee_group")
public class FeeGroup implements BeanLabel {

	@Id
	@GeneratedValue
	private int feeGroupId;
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "feeGroup")
	private Set<Fee> fees;

	public FeeGroup() {
	}

	@Override
	public String getLabel() {
		return this.name;
	}

	@Override
	public String getTooltip() {
		return Helper.getI18N(I18N.TOOLTIP_FEEGROUP, new String[] {
			this.name
		});
	}

	public Set<Fee> getFees() {
		HashSet<Fee> sortedSet = BusinessLogicSolver.sortFeeSet(this.fees);
		return sortedSet;
	}

	public void setFees(Set<Fee> fees) {
		this.fees = fees;
	}

	public int getFeeGroupId() {
		return this.feeGroupId;
	}

	public void setFeeGroupId(int feeGroupId) {
		this.feeGroupId = feeGroupId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
