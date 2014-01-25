package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.FeeDetails;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.embedded.ClassMonth;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class FeeDetailsDAO {
	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	public List<FeeDetails> getFeeDetails() {
		List<FeeDetails> feeDetails = new ArrayList<FeeDetails>();
		try {
			feeDetails = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_FEE_DETAILS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return feeDetails;
	}

	// FIXME
	// public FeeDetails getFeeDetails(int classId, int monthId) {
	// Classes associatedClass = (Classes) this.session.get(Classes.class,
	// Integer.valueOf(classId));
	// Month month = (Month) this.session.get(Month.class,
	// Integer.valueOf(monthId));
	// ClassMonth classMonth = new ClassMonth(associatedClass, month);
	//
	// FeeDetails feeDetails = null;
	// try {
	// feeDetails = (FeeDetails) this.session.get(FeeDetails.class,
	// classMonth);
	// }
	// catch (Exception e) {
	// e.printStackTrace();
	// }
	// return feeDetails;
	// }

	public FeeDetails getFeeDetails(int feeDetailsId) {
		FeeDetails feeDetails = null;
		try {
			feeDetails = (FeeDetails) this.session.get(FeeDetails.class,
					feeDetailsId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return feeDetails;
	}

	public void saveOrUpdateFeeDetails(FeeDetails feeDetails) {
		try {
			this.session.saveOrUpdate(feeDetails);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public void deleteFeeDetails(int feeDetailsId) {
		try {
			FeeDetails feeDetails = (FeeDetails) this.session.get(
					FeeDetails.class, feeDetailsId);
			this.session.delete(feeDetails);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}
}
