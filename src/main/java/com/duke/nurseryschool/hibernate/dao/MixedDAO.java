package com.duke.nurseryschool.hibernate.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.duke.nurseryschool.helper.FeeType;
import com.duke.nurseryschool.hibernate.bean.AlternativeFeeMap;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeeMap;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.Student;

public class MixedDAO extends CoreDAO {

	/**
	 * Get count of all classes that are available
	 */
	// SELECT COUNT(classId) FROM class WHERE grade != 4;
	// NOTICE: 0,1,2,3 ARE INCREMENTING VALUES AS ORDER IN GRADE ENUM. 4 IS FOR
	// UNIDENTIFIED
	@SuppressWarnings("rawtypes")
	public int getActiveClassesCount() {
		Query query = this.session
				.createSQLQuery("SELECT COUNT(classId) FROM class WHERE grade != 4;");
		List result = query.list();
		return ((BigInteger) result.get(0)).intValue();
	}

	// SELECT COUNT(studentId) FROM student RIGHT JOIN class ON student.classId
	// = class.classId WHERE class.grade != 4;
	@SuppressWarnings("rawtypes")
	public int getActiveStudentsCount() {
		Query query = this.session
				.createSQLQuery("SELECT COUNT(studentId) FROM student RIGHT JOIN class ON student.classId = class.classId WHERE class.grade != 4;");
		List result = query.list();
		return ((BigInteger) result.get(0)).intValue();
	}

	// SELECT * FROM student s RIGHT JOIN payment p ON p.studentId = s.studentId
	// RIGHT JOIN fee_policy fp ON fp.feePolicyId = p.feePolicyId WHERE
	// p.hasBreakfast = 1 AND fp.classId = 4;
	@SuppressWarnings("unchecked")
	public List<Student> getStudentsHavingBreakfast(int monthId) {
		String sql = "SELECT * FROM student s RIGHT JOIN payment p ON p.studentId = s.studentId RIGHT JOIN fee_policy fp ON fp.feePolicyId = p.feePolicyId  RIGHT JOIN class c ON s.`classId` = c.`classId` WHERE p.hasBreakfast = 1 AND fp.monthId = "
				+ monthId + " ORDER BY c.`code` ASC, s.`name` DESC ;";
		Query query = this.session.createSQLQuery(sql).addEntity(Student.class);
		return query.list();
	}

	// SELECT DISTINCT s.name as studentName, f.name as feeName from student s
	// right join payment p on s.studentId = p.studentId right join fee_policy
	// fp on fp.feePolicyId = p.feePolicyId right join alternative_fee_map a on
	// a.paymentId = p.paymentId
	// right join fee f on a.feeId = f.feeId where f.type = 2 and fp.monthId = 1
	// and f.feeId = 4;
	@SuppressWarnings("unchecked")
	public List<String> getStudentsHavingSelectedOnlyFee(int monthId, int feeId) {
		String sql = "SELECT DISTINCT s.name from student s right join payment p on s.studentId = p.studentId right join fee_policy fp on fp.feePolicyId = p.feePolicyId right join alternative_fee_map a on a.paymentId = p.paymentId right join fee f on a.feeId = f.feeId where f.type = "
				+ FeeType.SELECTED_ONLY.getType()
				+ " and fp.monthId = "
				+ monthId + " and f.feeId = " + feeId + ";";
		Query query = this.session.createSQLQuery(sql);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Payment> getPaymentsByFeePolicy(Session session, int feePolicyId) {
		String hql = "From Payment WHERE feePolicyId=" + feePolicyId;
		Query query = session.createQuery(hql);
		// query.setParameter("feePolicyId", Integer.valueOf(feePolicyId));
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Fee> getFees(Session session) {
		String hql = "FROM Fee";
		Query query = session.createQuery(hql);
		List<Fee> results = query.list();

		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Fee> getFeeWithNoGroup(Session session) {
		String sql = "SELECT * FROM fee WHERE feeGroupId IS NULL";
		Query query = session.createSQLQuery(sql).addEntity(Fee.class);
		List<Fee> results = query.list();

		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Fee> getFeeByType(Session session, FeeType feeType) {
		String hql = "FROM Fee F WHERE F.type = :typeId";
		Query query = session.createQuery(hql);
		query.setParameter("typeId", feeType);
		List<Fee> results = query.list();

		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Fee> getFeeByType(FeeType feeType) {
		String hql = "FROM Fee F WHERE F.type = :typeId";
		Query query = this.session.createQuery(hql);
		query.setParameter("typeId", feeType);
		List<Fee> results = query.list();

		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Fee> getFeeNotByType(FeeType feeType) {
		String hql = "FROM Fee F WHERE F.type != :typeId";
		Query query = this.session.createQuery(hql);
		query.setParameter("typeId", feeType);
		List<Fee> results = query.list();

		return results;
	}

	@SuppressWarnings("unchecked")
	public Payment getPaymentByStudentIdAndFeePolicyId(int studentId,
			int feePolicyId) {
		// String sql =
		// "SELECT * FROM payment P LEFT JOIN student S ON P.studentId = S.studentId WHERE P.studentId = :studentId AND P.feePolicyId = :feePolicyId";
		String sql = "SELECT * FROM payment P WHERE P.studentId = :studentId AND P.feePolicyId = :feePolicyId";
		SQLQuery query = this.session.createSQLQuery(sql);
		query.addEntity(Payment.class);
		// query.addEntity(Student.class);
		query.setParameter("studentId", studentId);
		query.setParameter("feePolicyId", feePolicyId);
		List<Payment> results = query.list();

		if (results.isEmpty())
			return null;

		return results.get(0);
	}

	@SuppressWarnings("unchecked")
	public FeeMap getFeeMapByFeeIdAndFeePolicyId(int feeId, int feePolicyId) {
		// SELECT * FROM fee_map fm WHERE fm.`feeId` = 1 AND fm.`feePolicyId` =
		// 1
		String sql = "SELECT * FROM fee_map fm WHERE fm.`feeId` = :feeId AND fm.`feePolicyId` = :feePolicyId";
		SQLQuery query = this.session.createSQLQuery(sql);
		query.addEntity(FeeMap.class);
		query.setParameter("feeId", feeId);
		query.setParameter("feePolicyId", feePolicyId);
		List<FeeMap> results = query.list();

		if (results.isEmpty())
			return null;

		return results.get(0);
	}

	@SuppressWarnings("unchecked")
	public AlternativeFeeMap getAlternativeFeeMapByFeeIdAndFeePolicyId(
			int feeId, int paymentId) {
		// SELECT * FROM alternative_fee_map afm WHERE afm.`feeId` = 1 AND
		// afm.`paymentId` =
		// 1
		String sql = "SELECT * FROM alternative_fee_map afm WHERE afm.`feeId` = :feeId AND afm.`paymentId` = :paymentId";
		SQLQuery query = this.session.createSQLQuery(sql);
		query.addEntity(AlternativeFeeMap.class);
		query.setParameter("feeId", feeId);
		query.setParameter("paymentId", paymentId);
		List<AlternativeFeeMap> results = query.list();

		if (results.isEmpty())
			return null;

		return results.get(0);
	}
}
