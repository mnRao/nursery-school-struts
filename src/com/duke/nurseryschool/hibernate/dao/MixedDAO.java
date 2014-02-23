package com.duke.nurseryschool.hibernate.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;

import com.duke.nurseryschool.core.CoreDAO;

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
}
