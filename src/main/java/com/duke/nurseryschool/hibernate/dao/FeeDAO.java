package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreDAO;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Fee;

public class FeeDAO extends CoreDAO {

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

	public boolean deleteFee(int feeId) {
		try {
			Fee fee = (Fee) this.session.get(Fee.class, feeId);
			this.session.delete(fee);
			this.session.flush();
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
