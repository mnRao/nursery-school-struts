package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.ExtraFeeType;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class ExtraFeeTypeDAO {
	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	public List<ExtraFeeType> getExtraFeeTypes() {
		List<ExtraFeeType> types = new ArrayList<ExtraFeeType>();
		try {
			types = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_EXTRA_FEE_TYPES).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return types;
	}

	public ExtraFeeType getExtraFeeType(int extraFeeTypeId) {
		ExtraFeeType type = null;
		try {
			type = (ExtraFeeType) this.session.get(ExtraFeeType.class,
					extraFeeTypeId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return type;
	}

	public void saveOrUpdateExtraFeeType(ExtraFeeType type) {
		try {
			this.session.saveOrUpdate(type);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public void deleteExtraFeeType(int extraFeeTypeId) {
		try {
			ExtraFeeType type = (ExtraFeeType) this.session.get(
					ExtraFeeType.class, extraFeeTypeId);
			this.session.delete(type);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}
}
