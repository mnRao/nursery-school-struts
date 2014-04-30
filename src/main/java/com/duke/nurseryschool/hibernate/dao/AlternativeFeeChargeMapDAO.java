package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.AlternativeFeeMap;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.embedded.PaymentFee;

public class AlternativeFeeChargeMapDAO extends CoreDAO {

	@SuppressWarnings("unchecked")
	public List<AlternativeFeeMap> getAlternativeFeeMaps() {
		List<AlternativeFeeMap> alternativeFeeMaps = new ArrayList<AlternativeFeeMap>();
		try {
			alternativeFeeMaps = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_ALTERNATIVE_FEE_MAPS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return alternativeFeeMaps;
	}

	public AlternativeFeeMap getAlternativeFeeMap(int paymentId, int feeId) {
		Payment payment = (Payment) this.session.get(Payment.class,
				Integer.valueOf(paymentId));
		Fee fee = (Fee) this.session.get(Fee.class, Integer.valueOf(feeId));
		PaymentFee paymentFee = new PaymentFee(payment, fee);

		AlternativeFeeMap alternativeFeeMap = null;
		try {
			alternativeFeeMap = (AlternativeFeeMap) this.session.get(
					AlternativeFeeMap.class, paymentFee);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return alternativeFeeMap;
	}

	public void saveOrUpdateAlternativeFeeMap(
			AlternativeFeeMap alternativeFeeMap) {
		try {
			this.session.saveOrUpdate(alternativeFeeMap);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}

		this.session.flush();
	}

	public void deleteAlternativeFeeMap(int paymentId, int feeId) {
		AlternativeFeeMap alternativeFeeMap = this.getAlternativeFeeMap(
				paymentId, feeId);
		if (alternativeFeeMap == null)
			return;

		if (!this.transaction.isActive()) {
			this.transaction.begin();
		}

		try {
			this.session.delete(alternativeFeeMap);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}

		this.session.flush();
	}

}
