package com.duke.nurseryschool.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public Month() {
	}

	public Month(int month, int year) {
		this.monthName = month;
		this.year = year;
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

}