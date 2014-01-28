package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Classes;
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

	public Classes getClasses(int classesId) {
		Classes classes = null;
		try {
			classes = (Classes) this.session.get(Classes.class,
					Integer.valueOf(classesId));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return classes;
	}

	public void saveOrUpdateClasses(Classes classes) {
		try {
			this.session.saveOrUpdate(classes);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public void deleteClasses(int classesId) {
		try {
			Classes classes = (Classes) this.session.get(Classes.class,
					classesId);
			this.session.delete(classes);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}
}