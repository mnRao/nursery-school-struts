package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.PaymentTrigger;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Payment;

public class FeePolicyDAO extends CoreDAO {

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
		// Set total fee before save
		Set<Payment> relatedPayments = feePolicy.getPayments();
		if (relatedPayments != null) {
			for (Payment payment : relatedPayments) {
				new PaymentTrigger(this.session, payment).calculateAndSetAll();
			}
		}

		try {
			this.session.saveOrUpdate(feePolicy);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public boolean deleteFeePolicy(int feePolicyId) {
		try {
			FeePolicy feePolicy = (FeePolicy) this.session.get(FeePolicy.class,
					feePolicyId);
			// REMOVE COMMENT WHEN ENABLING DELETE PROTECTION
			// if (feePolicy.getFeeMaps().size() > 0
			// || feePolicy.getPayments().size() > 0) {
			// return false;
			// }

			this.session.delete(feePolicy);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();

			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean hasDuplicates(int keyFeePolicyId, int classId, int monthId) {
		if (keyFeePolicyId != 0)
			return false;
		Criteria criteria = this.session.createCriteria(FeePolicy.class);
		criteria.add(Restrictions.eq("associatedClass.classId", classId));
		criteria.add(Restrictions.eq("month.monthId", monthId));
		List<FeePolicy> results = criteria.list();
		if (results != null && results.size() > 0) {
			for (FeePolicy result : results) {
				// If another record with different ID then true
				if (result.getFeePolicyId() != keyFeePolicyId) {
					return true;
				}
			}
		}
		return false;
	}
}
