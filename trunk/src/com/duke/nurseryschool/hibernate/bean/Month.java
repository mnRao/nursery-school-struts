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
	private int month;
	@Column(name = "year")
	private int year;

	public Month() {
	}

	public Month(int month, int year) {
		super();
		this.month = month;
		this.year = year;
	}

	public int getMonthId() {
		return this.monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
