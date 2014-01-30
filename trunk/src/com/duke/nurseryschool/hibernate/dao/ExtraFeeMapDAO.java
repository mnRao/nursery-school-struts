package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.BusinessLogicSolver;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.ExtraFeeMap;
import com.duke.nurseryschool.hibernate.bean.ExtraFeeType;
import com.duke.nurseryschool.hibernate.bean.FeeDetails;
import com.duke.nurseryschool.hibernate.bean.embedded.FeeDetailsExtraFee;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class ExtraFeeMapDAO {
	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	public List<ExtraFeeMap> getExtraFeeMaps() {
		List<ExtraFeeMap> extraFeeMaps = new ArrayList<ExtraFeeMap>();
		try {
			extraFeeMaps = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_EXTRA_FEE_MAPS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return extraFeeMaps;
	}

	public ExtraFeeMap getExtraFeeMap(int extraFeeTypeId, int feeDetailsId) {
		FeeDetails feeDetails = (FeeDetails) this.session.get(FeeDetails.class,
				Integer.valueOf(feeDetailsId));
		ExtraFeeType extraFeeType = (ExtraFeeType) this.session.get(
				ExtraFeeType.class, Integer.valueOf(extraFeeTypeId));
		FeeDetailsExtraFee feeDetailsExtraFee = new FeeDetailsExtraFee(
				feeDetails, extraFeeType);

		ExtraFeeMap extraFeeMap = null;
		try {
			extraFeeMap = (ExtraFeeMap) this.session.get(ExtraFeeMap.class,
					feeDetailsExtraFee);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return extraFeeMap;
	}

	public void saveOrUpdateExtraFeeMap(ExtraFeeMap extraFeeMap) {
		try {
			this.session.saveOrUpdate(extraFeeMap);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}

		// Flush before recalculating
		this.session.flush();
		BusinessLogicSolver.recalculateExtraFee(extraFeeMap
				.getFeeDetailsExtraFee().getFeeDetails().getFeeDetailsId(),
				this.session);
	}

	public void deleteExtraFeeMap(int extraId, int feeDetailsId) {
		ExtraFeeMap extraFeeMap = this.getExtraFeeMap(extraId, feeDetailsId);
		// double amountToSubstract = extraFeeMap.getAmount();
		try {
			this.session.delete(extraFeeMap);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}

		// Flush before recalculating
		this.session.flush();
		BusinessLogicSolver.recalculateExtraFee(feeDetailsId, this.session);
	}

}
