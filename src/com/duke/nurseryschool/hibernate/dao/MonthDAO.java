package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.comparator.MonthComparator;
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
			months = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_MONTHS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		// Sort
		Collections.sort(months, new MonthComparator());

		return months;
	}

	public Month getMonth(int monthId) {
		Month month = null;
		try {
			month = (Month) this.session.get(Month.class, monthId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return month;
	}

	public void saveOrUpdateMonth(Month month) {
		try {
			this.session.saveOrUpdate(month);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public void deleteMonth(int monthId) {
		try {
			Month month = (Month) this.session.get(Month.class, monthId);
			this.session.delete(month);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}
}
