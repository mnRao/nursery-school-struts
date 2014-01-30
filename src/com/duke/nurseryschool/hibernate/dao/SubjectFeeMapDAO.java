package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.BusinessLogicSolver;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.FeeDetails;
import com.duke.nurseryschool.hibernate.bean.Subject;
import com.duke.nurseryschool.hibernate.bean.SubjectFeeMap;
import com.duke.nurseryschool.hibernate.bean.embedded.SubjectFee;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class SubjectFeeMapDAO {
	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	public List<SubjectFeeMap> getSubjectFeeMaps() {
		List<SubjectFeeMap> subjectFeeMaps = new ArrayList<SubjectFeeMap>();
		try {
			subjectFeeMaps = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_SUBJECT_FEE_MAPS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return subjectFeeMaps;
	}

	public SubjectFeeMap getSubjectFeeMap(int subjectId, int feeDetailsId) {
		FeeDetails feeDetails = (FeeDetails) this.session.get(FeeDetails.class,
				Integer.valueOf(feeDetailsId));
		Subject subject = (Subject) this.session.get(Subject.class,
				Integer.valueOf(subjectId));
		SubjectFee subjectFee = new SubjectFee(feeDetails, subject);

		SubjectFeeMap subjectFeeMap = null;
		try {
			subjectFeeMap = (SubjectFeeMap) this.session.get(
					SubjectFeeMap.class, subjectFee);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return subjectFeeMap;
	}

	public void saveOrUpdateSubjectFeeMap(SubjectFeeMap subjectFeeMap) {
		try {
			this.session.saveOrUpdate(subjectFeeMap);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}

		// Flush before recalculating
		this.session.flush();
		BusinessLogicSolver.recalculateExtraStudyFee(subjectFeeMap
				.getSubjectFee().getFeeDetails().getFeeDetailsId(),
				this.session);
	}

	public void deleteSubjectFeeMap(int subjectId, int feeDetailsId) {
		SubjectFeeMap subjectFeeMap = this.getSubjectFeeMap(subjectId,
				feeDetailsId);
		// double amountToSubstract = subjectFeeMap.getAmount();
		try {
			this.session.delete(subjectFeeMap);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}

		// Flush before recalculating
		this.session.flush();
		BusinessLogicSolver
				.recalculateExtraStudyFee(feeDetailsId, this.session);
	}

}
