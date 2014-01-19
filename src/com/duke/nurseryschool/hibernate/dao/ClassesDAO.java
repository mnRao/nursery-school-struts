package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class ClassesDAO {
	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	public List<Classes> getClasses() {
		List<Classes> classes = new ArrayList<Classes>();
		try {
			classes = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_CLASSES).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return classes;
	}

	public void addClass(Classes classes) {
		this.session.save(classes);
	}
}
