package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.embedded.ClassMonth;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class FeePolicyDAO {
	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	public List<FeePolicy> getFeePolicies() {
		List<FeePolicy> feePolicies = new ArrayList<FeePolicy>();
		try {
			feePolicies = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_FEE_POLICIES).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return feePolicies;
	}

	public FeePolicy getFeePolicy(int feePolicyId) {
		FeePolicy feePolicy = null;
		try {
			feePolicy = (FeePolicy) this.session.get(FeePolicy.class,
					feePolicyId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return feePolicy;
	}

	public void saveOrUpdateFeePolicy(FeePolicy feePolicy) {
		try {
			this.session.saveOrUpdate(feePolicy);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public void deleteFeePolicy(int feePolicyId) {
		try {
			FeePolicy feePolicy = (FeePolicy) this.session.get(FeePolicy.class,
					feePolicyId);
			this.session.delete(feePolicy);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

}