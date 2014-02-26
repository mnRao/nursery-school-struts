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

	// SELECT * FROM student s RIGHT JOIN payment p ON p.studentId = s.studentId
	// RIGHT JOIN fee_policy fp ON fp.feePolicyId = p.feePolicyId WHERE
	// p.hasBreakfast = 1 AND fp.classId = 4;
	public List<String> getStudentsHavingBreakfast(int monthId) {
		String sql = "SELECT s.name FROM student s RIGHT JOIN payment p ON p.studentId = s.studentId RIGHT JOIN fee_policy fp ON fp.feePolicyId = p.feePolicyId WHERE p.hasBreakfast = 1 AND fp.monthId = "
				+ monthId + ";";
		Query query = this.session.createSQLQuery(sql);
		return query.list();
	}

	// SELECT DISTINCT s.name as studentName, f.name as feeName from student s
	// right join payment p on s.studentId = p.studentId right join fee_policy
	// fp on fp.feePolicyId = p.feePolicyId right join alternative_fee_map a on
	// a.paymentId = p.paymentId
	// right join fee f on a.feeId = f.feeId where f.type = 2 and fp.monthId = 1
	// and f.feeId = 4;
	public List<String> getStudentsHavingSelectedOnlyFee(int monthId, int feeId) {
		String sql = "SELECT DISTINCT s.name from student s right join payment p on s.studentId = p.studentId right join fee_policy fp on fp.feePolicyId = p.feePolicyId right join alternative_fee_map a on a.paymentId = p.paymentId right join fee f on a.feeId = f.feeId where f.type = 2 and fp.monthId = "
				+ monthId + " and f.feeId = " + feeId + ";";
		Query query = this.session.createSQLQuery(sql);
		return query.list();
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