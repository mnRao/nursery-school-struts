package com.duke.nurseryschool.hibernate.bean;

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

	@OneToMany(mappedBy = "extraFeeType")
	private ExtraFeeMap extraFeeMap;

	public ExtraFeeType() {
	}

	public ExtraFeeType(int extraFeeTypeId, String name, ExtraFeeMap extraFeeMap) {
		super();
		this.extraFeeTypeId = extraFeeTypeId;
		this.name = name;
		this.extraFeeMap = extraFeeMap;
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

	public ExtraFeeMap getExtraFeeMap() {
		return this.extraFeeMap;
	}

	public void setExtraFeeMap(ExtraFeeMap extraFeeMap) {
		this.extraFeeMap = extraFeeMap;
	}

}
