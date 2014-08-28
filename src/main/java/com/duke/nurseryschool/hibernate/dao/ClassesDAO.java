package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.duke.nurseryschool.helper.BusinessLogicSolver;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.Grade;
import com.duke.nurseryschool.helper.comparator.ClassComparator;
import com.duke.nurseryschool.hibernate.bean.Classes;

public class ClassesDAO extends CoreDAO {

	@SuppressWarnings("unchecked")
	public List<Classes> getClasses() {
		List<Classes> classes = new ArrayList<Classes>();
		try {
			classes = this.session.createQuery(Constant.DATABASE_QUERY.ALL_CLASSES).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		for (Classes singleClass : classes) {
			boolean needsAlternativeGrade = this.needsAlternativeGrade(singleClass);
			if (needsAlternativeGrade) {
				this.saveOrUpdateClasses(singleClass);
			}
		}

		// Sort
		Collections.sort(classes, new ClassComparator());

		return classes;
	}

	public Classes getClasses(int classesId) {
		Classes classes = null;
		try {
			classes = (Classes) this.session.get(Classes.class, Integer.valueOf(classesId));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// FIXME Avoid duplicate checking
		boolean needsAlternativeGrade = this.needsAlternativeGrade(classes);
		if (needsAlternativeGrade) {
			this.saveOrUpdateClasses(classes);
		}

		return classes;
	}

	public void saveOrUpdateClasses(Classes classes) {
		// Update grade
		this.needsAlternativeGrade(classes);

		try {
			this.session.saveOrUpdate(classes);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	private boolean needsAlternativeGrade(Classes classes) {
		if (classes == null)
			return false;

		Grade oldGrade = classes.getGrade();
		Grade calculatedGrade = Grade.UNIDENTIFIED;
		if (classes.getCourse() != null) {
			int startYear = classes.getCourse().getStartYear();
			int endYear = classes.getCourse().getEndYear();
			calculatedGrade = BusinessLogicSolver.getInstance().calculateGrade(startYear, endYear);
		}

		classes.setGrade(calculatedGrade);

		if (oldGrade == calculatedGrade)
			return false;
		else
			return true;
	}

	public boolean deleteClasses(int classesId) {
		try {
			Classes classes = (Classes) this.session.get(Classes.class, classesId);
			this.session.delete(classes);
			this.session.flush();
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean hasDuplicates(int classId, int courseId, String code) {
		if (classId != 0)
			return false;
		Criteria criteria = this.session.createCriteria(Classes.class);
		criteria.add(Restrictions.eq("course.courseId", courseId));
		criteria.add(Restrictions.eq("code", code));
		List<Classes> results = criteria.list();
		if (results != null && results.size() > 0) {
			for (Classes result : results) {
				// If another record with different ID then true
				if (result.getClassId() != classId) {
					return true;
				}
			}
		}
		return false;
	}
}
