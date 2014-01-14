package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class MonthDAO {

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	public List<Month> getMonths() {
		List<Month> months = new ArrayList<Month>();
		try {
			months = session.createQuery(Constant.DATABASE_QUERY.ALL_MONTHS)
					.list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return months;
	}

	public void addMonth(Month month) {
		session.save(month);
	}
}
