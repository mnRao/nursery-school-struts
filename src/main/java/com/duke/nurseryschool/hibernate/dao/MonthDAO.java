package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.comparator.MonthComparator;
import com.duke.nurseryschool.hibernate.bean.Month;

public class MonthDAO extends CoreDAO {

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

	@SuppressWarnings("unchecked")
	public boolean hasDuplicates(int keyMonthId, int monthName, int year) {
		if (keyMonthId != 0)
			return false;
		Criteria criteria = this.session.createCriteria(Month.class);
		criteria.add(Restrictions.eq("monthName", monthName));
		criteria.add(Restrictions.eq("year", year));
		List<Month> results = criteria.list();
		if (results != null && results.size() > 0) {
			for (Month month : results) {
				// If another record with different ID then true
				if (month.getMonthId() != keyMonthId) {
					return true;
				}
			}
		}
		return false;
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

	public boolean deleteMonth(int monthId) {
		try {
			Month month = (Month) this.session.get(Month.class, monthId);
			this.session.delete(month);
			this.session.flush();
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
