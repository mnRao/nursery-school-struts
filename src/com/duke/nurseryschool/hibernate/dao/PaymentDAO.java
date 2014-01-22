package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class PaymentDAO {
	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	public List<Payment> getPayments() {
		List<Payment> payments = new ArrayList<Payment>();
		try {
			payments = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_PAYMENTS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return payments;
	}

	public Payment getPayment(int paymentId) {
		Payment payment = null;
		try {
			payment = (Payment) this.session.get(Payment.class, paymentId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return payment;
	}

	public void saveOrUpdatePayment(Payment payment) {
		try {
			this.session.saveOrUpdate(payment);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public void deletePayment(int paymentId) {
		try {
			Payment payment = (Payment) this.session.get(Payment.class,
					paymentId);
			this.session.delete(payment);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}
}
