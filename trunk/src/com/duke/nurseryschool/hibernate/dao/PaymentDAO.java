package com.duke.nurseryschool.hibernate.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.duke.nurseryschool.core.CoreDAO;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.PaymentTrigger;
import com.duke.nurseryschool.hibernate.bean.Course;
import com.duke.nurseryschool.hibernate.bean.Payment;

public class PaymentDAO extends CoreDAO {

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
		// Set total fee before save
		new PaymentTrigger(this.session, payment).calculateAndSetAll();

		try {
			this.session.saveOrUpdate(payment);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public boolean deletePayment(int paymentId) {
		try {
			Payment payment = (Payment) this.session.get(Payment.class,
					paymentId);

			if (payment.getAlternativeFeeMaps().size() > 0) {
				return false;
			}

			this.session.delete(payment);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();

			return false;
		}

		return true;
	}

	public boolean hasDuplicates(int keyPaymentId, int studentId,
			int feePolicyId) {
		if (keyPaymentId != 0)
			return false;
		Criteria criteria = this.session.createCriteria(Payment.class);
		criteria.add(Restrictions.eq("student.studentId", studentId));
		criteria.add(Restrictions.eq("feePolicy.feePolicyId", feePolicyId));
		List<Payment> results = criteria.list();
		if (results != null && results.size() > 0) {
			for (Payment result : results) {
				// If another record with different ID then true
				if (result.getPaymentId() != keyPaymentId) {
					return true;
				}
			}
		}
		return false;
	}
}
