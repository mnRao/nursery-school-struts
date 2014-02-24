package com.duke.nurseryschool.hibernate.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.duke.nurseryschool.core.CoreDAO;
import com.duke.nurseryschool.helper.FeeType;
import com.duke.nurseryschool.hibernate.bean.Fee;

public class MixedDAO extends CoreDAO {

	// Get count of all classes that are available
	// SELECT COUNT(classId) FROM class WHERE grade != 0;
	public int getActiveClassesCount() {
		Query query = this.session
				.createSQLQuery("SELECT COUNT(classId) FROM class WHERE grade != 0;");
		List result = query.list();
		return ((BigInteger) result.get(0)).intValue();
	}

	// SELECT COUNT(studentId) FROM student RIGHT JOIN class ON student.classId
	// = class.classId WHERE class.grade != 0;
	public int getActiveStudentsCount() {
		Query query = this.session
				.createSQLQuery("SELECT COUNT(studentId) FROM student RIGHT JOIN class ON student.classId = class.classId WHERE class.grade != 0;");
		List result = query.list();
		return ((BigInteger) result.get(0)).intValue();
	}

	public List<Fee> getFees(Session session) {
		String hql = "FROM Fee";
		Query query = session.createQuery(hql);
		List<Fee> results = query.list();

		return results;
	}

	public List<Fee> getFeeByType(Session session, FeeType feeType) {
		String hql = "FROM Fee F WHERE F.type = :typeId";
		Query query = session.createQuery(hql);
		query.setParameter("typeId", feeType);
		List<Fee> results = query.list();

		return results;
	}

	public List<Fee> getFeeByType(FeeType feeType) {
		String hql = "FROM Fee F WHERE F.type = :typeId";
		Query query = this.session.createQuery(hql);
		query.setParameter("typeId", feeType);
		List<Fee> results = query.list();

		return results;
	}

	public List<Fee> getFeeNotByType(FeeType feeType) {
		String hql = "FROM Fee F WHERE F.type != :typeId";
		Query query = this.session.createQuery(hql);
		query.setParameter("typeId", feeType);
		List<Fee> results = query.list();

		return results;
	}

}
