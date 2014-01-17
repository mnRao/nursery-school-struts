package com.duke.nurseryschool.hibernate.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "extra_fee_type")
public class ExtraFeeType {
	@Id
	@GeneratedValue
	private int extraFeeTypeId;
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "feeDetailsExtraFee.extraFeeType")
	private Set<ExtraFeeMap> extraFeeMaps;

	public ExtraFeeType() {
	}

	public ExtraFeeType(int extraFeeTypeId, String name,
			Set<ExtraFeeMap> extraFeeMaps) {
		this.extraFeeTypeId = extraFeeTypeId;
		this.name = name;
		this.extraFeeMaps = extraFeeMaps;
	}

	public int getExtraFeeTypeId() {
		return this.extraFeeTypeId;
	}

	public void setExtraFeeTypeId(int extraFeeTypeId) {
		this.extraFeeTypeId = extraFeeTypeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ExtraFeeMap> getExtraFeeMap() {
		return this.extraFeeMaps;
	}

	public void setExtraFeeMap(Set<ExtraFeeMap> extraFeeMaps) {
		this.extraFeeMaps = extraFeeMaps;
	}

}
