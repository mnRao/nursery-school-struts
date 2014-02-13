package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class FeeDAO {

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	public List<Fee> getFees() {
		List<Fee> fees = new ArrayList<Fee>();
		try {
			fees = this.session.createQuery(Constant.DATABASE_QUERY.ALL_FEES)
					.list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return fees;
	}

	public Fee getFee(int feeId) {
		Fee fee = null;
		try {
			fee = (Fee) this.session.get(Fee.class, feeId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return fee;
	}

	public void saveOrUpdateFee(Fee fee) {
		try {
			this.session.saveOrUpdate(fee);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public void deleteFee(int feeId) {
		try {
			Fee fee = (Fee) this.session.get(Fee.class, feeId);
			this.session.delete(fee);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}
}
