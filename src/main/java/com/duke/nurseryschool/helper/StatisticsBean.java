package com.duke.nurseryschool.helper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Month;

public class StatisticsBean {

	private Month month;
	private Map<Classes, BigDecimal> totalFeeForClasses = new HashMap<Classes, BigDecimal>();
	private BigDecimal totalFeeForSchool = new BigDecimal(0);

	public StatisticsBean(Month month) {
		this.month = month;
	}

	public void addTotalFee(Classes classes, BigDecimal totalFeeForClass) {
		this.totalFeeForClasses.put(classes, totalFeeForClass);
		// Automatically compute total fee for school
		this.totalFeeForSchool = this.totalFeeForSchool.add(totalFeeForClass);
	}

	public Month getMonth() {
		return month;
	}

	public Map<Classes, BigDecimal> getTotalFeeForClasses() {
		return totalFeeForClasses;
	}

	public BigDecimal getTotalFeeForSchool() {
		return totalFeeForSchool;
	}

}
