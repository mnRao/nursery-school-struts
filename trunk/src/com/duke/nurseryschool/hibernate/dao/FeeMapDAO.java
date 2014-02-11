package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.BusinessLogicSolver;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeeMap;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.embedded.FeePolicyFee;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class FeeMapDAO {
	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	public List<FeeMap> getFeeMaps() {
		List<FeeMap> feeMaps = new ArrayList<FeeMap>();
		try {
			feeMaps = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_FEE_MAPS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return feeMaps;
	}

	public FeeMap getFeeMap(int feeId, int feePolicyId) {
		FeePolicy feePolicy = (FeePolicy) this.session.get(FeePolicy.class,
				Integer.valueOf(feePolicyId));
		Fee fee = (Fee) this.session.get(Fee.class, Integer.valueOf(feeId));
		FeePolicyFee feePolicyFee = new FeePolicyFee(feePolicy, fee);

		FeeMap feeMap = null;
		try {
			feeMap = (FeeMap) this.session.get(FeeMap.class, feePolicyFee);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return feeMap;
	}

	public void saveOrUpdateFeeMap(FeeMap feeMap) {
		try {
			this.session.saveOrUpdate(feeMap);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}

		// Flush before recalculating
		this.session.flush();
		// TODO
		// BusinessLogicSolver.recalculateFee(feeMap.getFeePolicyFee()
		// .getFeePolicy().getFeePolicyId(), this.session);
	}

	public void deleteFeeMap(int feeId, int feePolicyId) {
		FeeMap feeMap = this.getFeeMap(feeId, feePolicyId);

		try {
			this.session.delete(feeMap);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}

		// Flush before recalculating
		this.session.flush();
		// TODO
		// BusinessLogicSolver.recalculateFee(feePolicyId, this.session);
	}

}
