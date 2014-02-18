package com.duke.nurseryschool.core;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class CoreDAO {
	@SessionTarget
	protected Session		session;

	@TransactionTarget
	protected Transaction	transaction;

	public Session getSession() {
		return this.session;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public boolean isSessionNull() {
		return this.session == null;
	}
}
