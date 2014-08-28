package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeeMap;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.embedded.FeePolicyFee;

public class FeeMapDAO extends CoreDAO {

	@SuppressWarnings("unchecked")
	public List<FeeMap> getFeeMaps() {
		List<FeeMap> feeMaps = new ArrayList<FeeMap>();
		try {
			feeMaps = this.session.createQuery(Constant.DATABASE_QUERY.ALL_FEE_MAPS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return feeMaps;
	}

	public FeeMap getFeeMap(int feeId, int feePolicyId) {
		FeePolicy feePolicy = (FeePolicy) this.session.get(FeePolicy.class, Integer.valueOf(feePolicyId));
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
	}

	public void deleteFeeMap(int feeId, int feePolicyId) {
		FeeMap feeMap = this.getFeeMap(feeId, feePolicyId);
		if (feeMap == null)
			return;

		if (!this.transaction.isActive()) {
			this.transaction.begin();
		}

		try {
			this.session.delete(feeMap);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}

		// Flush before recalculating
		this.session.flush();
	}

}
