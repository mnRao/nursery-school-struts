package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Subject;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class SubjectDAO {

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	public List<Subject> getSubjects() {
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			subjects = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_SUBJECTS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return subjects;
	}

	public Subject getSubject(int subjectId) {
		Subject subject = null;
		try {
			subject = (Subject) this.session.get(Subject.class, subjectId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return subject;
	}

	public void saveOrUpdateSubject(Subject subject) {
		try {
			this.session.saveOrUpdate(subject);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public void deleteSubject(int subjectId) {
		try {
			Subject subject = (Subject) this.session.get(Subject.class,
					subjectId);
			this.session.delete(subject);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}
}
