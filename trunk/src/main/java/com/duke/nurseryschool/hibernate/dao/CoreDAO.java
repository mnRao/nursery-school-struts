package com.duke.nurseryschool.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public abstract class CoreDAO {
	@SessionTarget
	protected Session session;

	@TransactionTarget
	protected Transaction transaction;

	public void setSession(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return this.session;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

}
