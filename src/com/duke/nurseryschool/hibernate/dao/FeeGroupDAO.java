package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.FeeGroup;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class FeeGroupDAO {
	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	public List<FeeGroup> getFeeGroups() {
		List<FeeGroup> feeGroups = new ArrayList<FeeGroup>();
		try {
			feeGroups = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_FEE_GROUPS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return feeGroups;
	}

	public FeeGroup getFeeGroup(int feeGroupId) {
		FeeGroup feeGroup = null;
		try {
			feeGroup = (FeeGroup) this.session.get(FeeGroup.class, feeGroupId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return feeGroup;
	}

	public void saveOrUpdateFeeGroup(FeeGroup feeGroup) {
		try {
			this.session.saveOrUpdate(feeGroup);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public void deleteFeeGroup(int feeGroupId) {
		try {
			FeeGroup feeGroup = (FeeGroup) this.session.get(FeeGroup.class,
					feeGroupId);
			this.session.delete(feeGroup);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}
}
