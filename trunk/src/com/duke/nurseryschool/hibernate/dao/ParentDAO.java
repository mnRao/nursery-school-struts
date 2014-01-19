package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Parent;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class ParentDAO {
	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	public List<Parent> getMonths() {
		List<Parent> parents = new ArrayList<Parent>();
		try {
			parents = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_PARENTS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return parents;
	}

	public void addParent(Parent parent) {
		this.session.save(parent);
	}
}
