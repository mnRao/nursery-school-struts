package com.duke.nurseryschool.hibernate.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.duke.nurseryschool.helper.BusinessLogicSolver;
import com.duke.nurseryschool.helper.Constant;

@Entity
@Table(name = "month")
public class Month {
	@Id
	@GeneratedValue
	private int monthId;
	@Column(name = "month")
	private int monthName;// Avoid using "month" => might be misunderstood
	@Column(name = "year")
	private int year;

	@OneToMany(mappedBy = "month")
	private Set<FeePolicy> feePolicies;

	public Month() {
	}

	public Month(int month, int year) {
		this.monthName = month;
		this.year = year;
	}

	public String getLabel() {
		return Constant.PUNCTUATION_MARK.BRACKET_SQUARE_OPEN + this.year
				+ Constant.PUNCTUATION_MARK.HYPHEN
				+ BusinessLogicSolver.getStandardMonthName(this.monthName)
				+ Constant.PUNCTUATION_MARK.BRACKET_SQUARE_CLOSE;
	}

	public int getMonthId() {
		return this.monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonthName() {
		return this.monthName;
	}

	public void setMonthName(int monthName) {
		this.monthName = monthName;
	}

	public Set<FeePolicy> getFeePolicies() {
		return this.feePolicies;
	}

	public void setFeePolicies(Set<FeePolicy> feePolicies) {
		this.feePolicies = feePolicies;
	}

}